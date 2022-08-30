package com.draw.drawmusic.note;

import com.draw.drawmusic.note_properties.Ticks;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public abstract class MusicNote implements Comparable<MusicNote> {
    protected int pitch;
    protected double velocity;
    protected Ticks timeOn, timeOff;
    protected static final double NOTE_HEIGHT = 20.0;

    public Rectangle toShape(NoteSelect isSelected) {
        Rectangle ret = new Rectangle(0, new Random().nextDouble(500), 100, NOTE_HEIGHT);
        System.out.println(ret.getY());

        return ret;
    }

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
