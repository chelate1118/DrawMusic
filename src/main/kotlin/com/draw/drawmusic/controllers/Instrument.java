package com.draw.drawmusic.controllers;

import com.draw.drawmusic.MainApplicationManager;
import javafx.scene.image.Image;

import java.util.Objects;

public class Instrument {
    private final int number;
    private static final String[] imageFileName = {"grand-piano.png"};

    private Instrument(int _num) { number = _num; }

    public static Instrument Grandpiano = new Instrument(0);

    public Image toIcon() {
        return new Image(Objects.requireNonNull(
                MainApplicationManager.Companion.getResourceAsString("images/" + imageFileName[number])));
    }
}
