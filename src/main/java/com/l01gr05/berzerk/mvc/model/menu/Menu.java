package com.l01gr05.berzerk.mvc.model.menu;

import com.l01gr05.berzerk.Game;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public abstract class Menu {
    private final String title;
    private final List<String> options;
    private final int numberOfOptions;
    private int currentOption;

    public Menu(String title, List<String> options) {
        this.title = title;
        this.options = options;
        this.numberOfOptions = options.size();
        this.currentOption = 0;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getNumberOfOptions() {
        return numberOfOptions;
    }

    public int getCurrentOption() {
        return currentOption;
    }

    public void setCurrentOption(int currentOption) {
        this.currentOption = currentOption;
    }

    public abstract void executeOption(Game game) throws IOException;
}
