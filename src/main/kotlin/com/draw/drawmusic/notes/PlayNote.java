package com.draw.drawmusic.notes;

import java.io.IOException;

public abstract class PlayNote extends MusicNote{
    public int ID;
    private Editor parent;
    public PlayNote(Editor _parent) {
        super();
        parent = _parent;
    }
    public void play() throws IOException {
        Play.playOne(this);
    }
}
