package com.draw.drawmusic.notes;

import javafx.scene.shape.Rectangle;

public class PianoNote extends MusicNote {
    @Override
    public Rectangle toShape() {
        return new Rectangle(0, 0, 100, 100);
    }

    @Override
    public void playImmediate() {

    }

    @Override
    public void play() {

    }
}
