package com.draw.drawmusic.track;

import com.draw.drawmusic.notes.Editor;
import com.draw.drawmusic.properties.Instrument;
import com.draw.drawmusic.properties.Palette;

public class Track {
    private static int CURRENT_ID = 0;
    public TrackBarElementController trackElement;
    public TrackSelect trackSelect;
    public Editor editor;
    public Palette palette;
    public Instrument instrument;
    public int ID;

    public Track(TrackBarElementController element, TrackSelect trackSelect, Palette palette) {
        this.ID = ++CURRENT_ID;
        this.trackElement = element;
        this.trackSelect = trackSelect;
        this.palette = palette;
        this.instrument = Instrument.DEFAULT;
        this.editor = new Editor(this);
    }
}