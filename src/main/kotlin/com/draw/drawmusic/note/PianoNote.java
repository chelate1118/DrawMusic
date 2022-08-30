package com.draw.drawmusic.note;

import com.draw.drawmusic.track.Track;
import javafx.scene.shape.Rectangle;

public class PianoNote extends PlayableNote {
    public PianoNote(Track _parent) {
        super(_parent);
    }

    public ViolinNote toViolinNote() {
        return null;
    }

    @Override
    public Rectangle toShape(NoteSelect isSelected) {
        Rectangle ret = super.toShape(isSelected);
        if(noteSelect == NoteSelect.Invisible) return null;
        return ret;
    }
}
