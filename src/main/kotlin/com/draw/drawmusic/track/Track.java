package com.draw.drawmusic.track;

import com.draw.drawmusic.notes.Editor;
import com.draw.drawmusic.properties.Instrument;
import com.draw.drawmusic.properties.Palette;

public class Track {
    public TrackBarElementController trackElement;
    public TrackSelect trackSelect;
    public Editor editor;
    public Palette palette;
    public Instrument instrument;

    public Track(TrackBarElementController element, TrackSelect trackSelect, Palette palette) {
        this.trackElement = element;
        this.trackSelect = trackSelect;
        this.palette = palette;
        this.instrument = Instrument.GRANDPIANO;
        this.editor = new Editor(this);
    }
}