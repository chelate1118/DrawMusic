package com.draw.drawmusic.note;

import com.draw.drawmusic.note_properties.MidiNote;
import com.draw.drawmusic.note_properties.Ticks;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.NotNull;

public abstract class MusicNote implements Comparable<MusicNote> {
    protected int pitch;
    protected double velocity;
    protected Ticks timeOn, timeOff;

    public Rectangle toShape() {
        return new Rectangle(0, MidiNote.pitchToY(pitch), 100, MidiNote.NOTE_HEIGHT);
    }

    public void setPitch(int pitch) {
        this.pitch = pitch;
    }

    @Override
    public int compareTo(@NotNull MusicNote o) {
        return timeOn.compareTo(o.timeOn);
    }
}
