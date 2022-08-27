package com.draw.drawmusic.notes;

import com.draw.drawmusic.track.TrackBarElement;

import java.io.IOException;

public abstract class PlayNote extends MusicNote{
    public int ID;
    private TrackBarElement parent;
    public PlayNote(TrackBarElement _parent) {
        super();
        parent = _parent;
    }
    public void play() throws IOException {
        Play.playOne(this);
    }
}
