package com.draw.drawmusic.note;

import com.draw.drawmusic.track.Track;
import javafx.scene.shape.Rectangle;

public class PianoNote extends PlayableNote {

    public PianoNote(Track _parent, double _timeOn, double _timeOff, int _pitch) {
        super(_parent);
        timeOn = _timeOn;
        timeOff = _timeOff;
        pitch = _pitch;
    }

    public ViolinNote toViolinNote() {
        return null;
    }

    @Override
    public Rectangle toShape() {
        return super.toShape();
    }
}
