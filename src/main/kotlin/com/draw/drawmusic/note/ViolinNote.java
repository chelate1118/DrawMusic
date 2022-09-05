package com.draw.drawmusic.note;

import com.draw.drawmusic.track.Track;
import javafx.scene.shape.Rectangle;

public class ViolinNote extends PlayableNote {
    public ViolinNote(Track _parent) {
        super(_parent);
    }

    public PianoNote toPianoNote() {
        return null;
    }
    @Override
    public Rectangle toShape() {
        return null;
    }
}
