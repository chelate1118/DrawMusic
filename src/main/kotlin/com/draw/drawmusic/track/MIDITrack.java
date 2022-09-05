package com.draw.drawmusic.track;

import com.draw.drawmusic.editor.MidiEditor;
import com.draw.drawmusic.track_properties.Instrument;
import com.draw.drawmusic.track_properties.Palette;
import com.draw.drawmusic.tools.Order;

public class MIDITrack extends Track{
    public Instrument instrument;
    public MIDITrack(TrackController trackElement, TrackSelect trackSelect, Palette palette, Order orderInTrackBar) {
        super(trackElement, trackSelect, palette, new MidiEditor(), orderInTrackBar);

        this.instrument = Instrument.DEFAULT;
        this.editor.setParent(this);
    }
}
