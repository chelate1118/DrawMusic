package com.draw.drawmusic.notes;

import javafx.scene.shape.Rectangle;

public abstract class MusicNote {
    public abstract Rectangle toShape();
    public abstract void playImmediate();
    public abstract void play();
}
