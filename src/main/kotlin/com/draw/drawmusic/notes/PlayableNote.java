package com.draw.drawmusic.notes;

import com.draw.drawmusic.track.Track;

import java.io.IOException;

public abstract class PlayableNote extends MusicNote{
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
}
