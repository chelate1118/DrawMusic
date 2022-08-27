package com.draw.drawmusic.notes;

import com.draw.drawmusic.track.Track;

import java.io.IOException;

public abstract class PlayNote extends MusicNote{
    public int ID;
    private Track parent;
    public PlayNote(Track _parent) {
        super();
        parent = _parent;
    }
    public void play() throws IOException {
        Play.playOne(this);
    }
}
