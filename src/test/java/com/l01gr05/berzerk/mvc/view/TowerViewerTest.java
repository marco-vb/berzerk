package com.l01gr05.berzerk.mvc.view;

import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.elements.Tower;
import com.l01gr05.berzerk.mvc.view.game.ElementViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TowerViewerTest {
    private ElementViewer towerViewer;
    private GUI gui;

    @BeforeEach
    void setUp()  {
        Tower tower = new Tower(new Position(10, 10));
        towerViewer = tower.getViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void testDraw() {
        towerViewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawTower(towerViewer.getModel());
    }
}
