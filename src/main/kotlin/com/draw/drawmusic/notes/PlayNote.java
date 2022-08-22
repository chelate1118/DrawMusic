package com.draw.drawmusic.notes;

import java.io.IOException;

public abstract class PlayNote extends MusicNote{
    public int ID;
    public void play() throws IOException {
        Play.playOne(this);
    }
}
