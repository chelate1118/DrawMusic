package com.draw.drawmusic.notes;

import com.draw.drawmusic.track.Track;
import javafx.scene.shape.Rectangle;

public class PianoNote extends PlayNote {
    public PianoNote(Track _parent) {
        super(_parent);
    }

    public ViolinNote toViolinNote() {
        return null;
    }

    @Override
    public Rectangle toShape(NoteSelect isSelected) {
        return super.toShape(isSelected);
    }
}
