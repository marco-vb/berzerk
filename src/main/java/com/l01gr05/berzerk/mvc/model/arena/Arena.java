package com.l01gr05.berzerk.mvc.model.arena;

import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.elements.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.net.URL;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width;
    private int height;
    private int level;
    private Agent agent;
    private Exit exit;
    private List<Enemy> enemies;
    private List<Wall> walls;

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
        this.enemies = new ArrayList<>();
        this.walls = new ArrayList<>();
    }

    public int getWidth() {return width;}

    public int getHeight() {return height;}

    public int getLevel() {return level;}

    public Agent getAgent() {return agent;}

    public List<Enemy> getEnemies() {return enemies;}

    public List<Wall> getWalls() {return walls;}

    public Exit getExit() {return exit;}

    // set level
    public void setLevel(int level) {
        this.level = level;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void addWall(Wall wall) {
        walls.add(wall);
    }

    public void addElement(Element element) {
        if (element instanceof Agent) {
            agent = (Agent) element;
        } else if (element instanceof Exit) {
            exit = (Exit) element;
        } else if (element instanceof Enemy) {
            enemies.add((Enemy) element);
        } else if (element instanceof Wall) {
            walls.add((Wall) element);
        }
    }

    public List<Element> getElements() {
        List<Element> elements = new ArrayList<>();
        elements.add(agent);
        elements.add(exit);
        elements.addAll(enemies);
        elements.addAll(walls);
        return elements;
    }

    public boolean isWall(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEnemy(Position position) {
        for (Enemy enemy : enemies) {
            if (enemy.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    public boolean isExit(Position position) {
        return exit.getPosition().equals(position);
    }

    public boolean isAgent(Position position) {
        return agent.getPosition().equals(position);
    }
}
