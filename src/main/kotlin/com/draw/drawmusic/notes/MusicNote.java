package com.draw.drawmusic.notes;

import com.draw.drawmusic.tools.Ticks;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.NotNull;

public abstract class MusicNote implements Comparable<MusicNote> {
    public int pitch;
    public double velocity;
    public Ticks timeOn, timeOff;

    public abstract Rectangle toShape();

    public static double pitchToY(int pitch) {
        return pitch;
    }

    public static int YToPitch(double Y) {
        return (int)Y;
    }

    @Override
    public int compareTo(@NotNull MusicNote o) {
        return timeOn.compareTo(o.timeOn);
    }
}
