package com.draw.drawmusic.track;

import com.draw.drawmusic.notes.Editor;
import com.draw.drawmusic.properties.Instrument;
import com.draw.drawmusic.properties.Palette;

public class Track {
    private static int NEXT_ID = 1;
    public TrackBarElementController trackElement;
    public TrackSelect trackSelect;
    public Editor editor;
    public Palette palette;
    public Instrument instrument;
    public int ID;

    public Track(TrackBarElementController trackElement, TrackSelect trackSelect, Palette palette) {
        this.ID = NEXT_ID++;
        this.trackElement = trackElement;
        this.trackSelect = trackSelect;
        this.palette = palette;
        this.instrument = Instrument.DEFAULT;
        this.editor = new Editor(this);
    }
}