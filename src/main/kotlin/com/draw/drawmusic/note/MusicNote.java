package com.draw.drawmusic.note;

import javafx.scene.shape.Shape;
import org.jetbrains.annotations.NotNull;

public abstract class MusicNote implements Comparable<MusicNote> {
    protected int pitch;
    protected double velocity;
    protected double timeOn, timeOff;

    public abstract Shape toShape();

    public void setPitch(int pitch) {
        this.pitch = pitch;
    }

    @Override
    public int compareTo(@NotNull MusicNote o) {
        return Double.compare(timeOn, o.timeOn);
    }
}
