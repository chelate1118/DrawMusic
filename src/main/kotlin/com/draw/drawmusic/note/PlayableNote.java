package com.draw.drawmusic.note;

import com.draw.drawmusic.track.Track;
import javafx.scene.shape.Rectangle;

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

    @Override
    public Rectangle toShape() {
        Rectangle rect = super.toShape();
        rect.setFill(parent.palette.color());

        return rect;
    }
}
