package com.draw.drawmusic.notes;

import javafx.scene.shape.Rectangle;

public class PianoNote extends PlayNote {
    @Override
    public Rectangle toShape() {
        return new Rectangle(0, 0, 100, 100);
    }
}
