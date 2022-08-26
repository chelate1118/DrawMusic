package com.draw.drawmusic.notes;

import com.draw.drawmusic.properties.Palette;
import com.draw.drawmusic.times.Ticks;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.NotNull;

public abstract class MusicNote implements Comparable<MusicNote> {
    protected int pitch;
    protected double velocity;
    protected Ticks timeOn, timeOff;
    protected Palette palette;
    protected static final double NOTE_HEIGHT = 20.0;

    public void setPalette(Palette palette) {
        this.palette = palette;
    }

    public Palette getPalette() {
        return palette;
    }

    public abstract Rectangle toShape(NoteSelect isSelected);

    public static double pitchToY(int pitch) {
        int i = (108 - pitch) / 12;
        int j = (108 - pitch) % 12;

        return (i * 7 + new double[]{0, 1, 1.5, 2, 2.5, 3, 3.5, 4, 5, 5.5, 6, 6.5}[j]) * NOTE_HEIGHT;
    }

    public static int YToPitch(double Y) {
        return (int)Y;
    }

    @Override
    public int compareTo(@NotNull MusicNote o) {
        return timeOn.compareTo(o.timeOn);
    }
}
