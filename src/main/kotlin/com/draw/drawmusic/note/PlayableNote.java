package com.draw.drawmusic.note;

import com.draw.drawmusic.note_properties.MidiNote;
import com.draw.drawmusic.track.Track;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public abstract class PlayableNote extends MusicNote {
    public int ID;
    protected Track parent;
    protected NoteSelect noteSelect;

    public PlayableNote(Track _parent) {
        super();
        parent = _parent;
    }

    public void play() throws IOException {
        Play.playOne(this);
    }

    @Override
    public Rectangle toShape() {
        pitch = MidiNote.MAX_PITCH - 2;

        Rectangle rect = new Rectangle(timeOn, MidiNote.pitchToY(pitch), timeOff, MidiNote.getNoteHeight(pitch));
        rect.setStroke(Color.BLACK);
        rect.setFill(parent.getPalette().color());

        return rect;
    }
}
