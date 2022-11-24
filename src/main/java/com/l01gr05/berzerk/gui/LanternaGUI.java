package com.l01gr05.berzerk.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.l01gr05.berzerk.mvc.model.elements.Element;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaGUI implements GUI {
    private final Screen screen;

    public LanternaGUI(int width, int height) throws IOException, URISyntaxException, FontFormatException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        Terminal terminal = createTerminal(width, height, fontConfig);
        this.screen = createScreen(terminal);
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        return terminalFactory.createTerminal();
    }

    private AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, IOException, FontFormatException {
        URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
        assert resource != null;
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 25);
        return AWTTerminalFontConfiguration.newInstance(loadedFont);
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }

    public INPUT getInput() throws IOException {
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke == null) {
            return INPUT.NONE;
        }
        KeyType keyType = keyStroke.getKeyType();

        switch (keyType) {
            case ArrowUp:
                return INPUT.UP;
            case ArrowDown:
                return INPUT.DOWN;
            case ArrowLeft:
                return INPUT.LEFT;
            case ArrowRight:
                return INPUT.RIGHT;
            case Escape:
                return INPUT.QUIT;
            case Enter:
                return INPUT.ENTER;
            case Character:
                if (keyStroke.getCharacter() == ' ') {
                    return INPUT.SHOOT;
                }
            default:
                return INPUT.NONE;
        }
    }

    @Override
    public void drawWall(Element model) {
        draw(model.getPosition().getX(), model.getPosition().getY(), '#');
    }

    @Override
    public void drawAgent(Element model) {
        draw(model.getPosition().getX(), model.getPosition().getY(), 'A');
    }

    @Override
    public void drawEnemy(Element model) {
        draw(model.getPosition().getX(), model.getPosition().getY(), 'E');
    }

    @Override
    public void draw(int x, int y, char c) {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.putString(x, y, String.valueOf(c));
    }
}
