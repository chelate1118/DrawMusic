package com.draw.drawmusic.history;

import com.draw.drawmusic.track.Track;
import com.draw.drawmusic.track.TrackBar;

public class AddTrack extends Behaviors {
    Track objectAdded;

    protected AddTrack(Track objectAdded) {
        super();
        this.objectAdded = objectAdded;
    }

    public static void saveNewHistory(Track objectAdded) {
        new AddTrack(objectAdded);
    }

    @Override
    public void undoMethod() {
        TrackBar.deleteElement(objectAdded);
    }

    @Override
    public void redoMethod() {
        TrackBar.addElement(objectAdded);
    }
}
