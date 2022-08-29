package com.draw.drawmusic.history;

import com.draw.drawmusic.track.Track;
import com.draw.drawmusic.track.TrackBar;

public class DeleteTrack extends Behaviors{
    Track objectAdded;

    protected DeleteTrack(Track objectAdded) {
        super();
        this.objectAdded = objectAdded;
    }

    public static void saveNewHistory(Track objectAdded) {
        new DeleteTrack(objectAdded);
    }

    @Override
    public void undoMethod() {
        TrackBar.addElement(objectAdded);
    }

    @Override
    public void redoMethod() {
        TrackBar.deleteElement(objectAdded);
    }
}
