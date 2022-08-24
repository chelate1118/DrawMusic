package com.draw.drawmusic.notes;

import com.draw.drawmusic.properties.Palette;
import com.draw.drawmusic.times.Ticks;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.NotNull;

public abstract class MusicNote implements Comparable<MusicNote> {
    public int pitch;
    public double velocity;
    public Ticks timeOn, timeOff;
    protected Palette palette;

    public void setPalette(Palette palette) {
        this.palette = palette;
    }

    public Palette getPalette() {
        return palette;
    }

    public abstract Rectangle toShape(boolean isSelected);

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
