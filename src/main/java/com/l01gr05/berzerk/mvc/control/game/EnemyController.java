package com.l01gr05.berzerk.mvc.control.game;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.Controller;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Enemy;
import com.l01gr05.berzerk.mvc.model.elements.EnemyBullet;

import java.util.Random;

public class EnemyController extends Controller<Arena> {
    public EnemyController(Arena arena) {
        super(arena);
    }

    @Override
    public void update(Game game, GUI.INPUT action) {
        for (Enemy enemy : getModel().getEnemies()) {
            enemy.move(getModel());
        }
        double shooting_probability = 0.1;
        for (Enemy enemy : getModel().getEnemies()) {
            if (Math.random() < shooting_probability) {
                char[] directions = {'N', 'S', 'E', 'W'};
                Random random = new Random();
                char direction = directions[random.nextInt(directions.length)];
                getModel().addElement(new EnemyBullet(enemy.getPosition(), direction));
            }
        }
    }
}
