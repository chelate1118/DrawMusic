package com.draw.drawmusic.track;

import com.draw.drawmusic.editor.MidiEditor;
import com.draw.drawmusic.tools.Order;
import com.draw.drawmusic.track_properties.Instrument;
import com.draw.drawmusic.track_properties.Palette;

public class MIDITrack extends Track{
    public Instrument instrument;
    public MIDITrack(TrackController trackElement, TrackSelect trackSelect, Palette palette, Order orderInTrackBar) {
        super(trackElement, trackSelect, palette, orderInTrackBar);

        this.editor = new MidiEditor(this);
        this.instrument = Instrument.DEFAULT;
    }
}
