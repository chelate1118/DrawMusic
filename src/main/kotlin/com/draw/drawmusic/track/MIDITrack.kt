package com.draw.drawmusic.track

import com.draw.drawmusic.editor.MidiEditor
import com.draw.drawmusic.tools.Order
import com.draw.drawmusic.track_properties.Instrument
import com.draw.drawmusic.track_properties.Palette

class MIDITrack(
    trackElement: TrackController,
    trackSelect: TrackSelect,
    palette: Palette,
    orderInTrackBar: Order
): Track(
    trackElement = trackElement,
    trackSelect = trackSelect,
    palette = palette,
    orderInTrackBar = orderInTrackBar
)  {
    var instrument = Instrument.DEFAULT

    init {
        editor = MidiEditor(this)
    }
}