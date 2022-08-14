package com.draw.drawmusic.properties;

import javafx.scene.image.Image;

public class Instrument {
    private static final String[] imageFileName = {"grand-piano.png"};
    private final int number; // index of imageFileName

    private Instrument(int _num) { number = _num; }

    public static Instrument Grandpiano = new Instrument(0);

    public Image toIcon() {
        return new Image(ResourcePaths.getAbsolutePath("images", imageFileName[number]));
    }
}
