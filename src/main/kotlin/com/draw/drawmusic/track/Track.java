package com.draw.drawmusic.track;

import com.draw.drawmusic.notes.Editor;
import com.draw.drawmusic.properties.Instrument;
import com.draw.drawmusic.properties.Palette;
import com.draw.drawmusic.tools.Order;
import org.jetbrains.annotations.NotNull;

public class Track implements Comparable<Track>{
    private static int NEXT_ID = 1;
    public TrackBarElementController trackElement;
    public TrackSelect trackSelect;
    public Editor editor;
    public Palette palette;
    public Instrument instrument;
    public int ID;
    public Order orderInTrackBar;

    public Track(TrackBarElementController trackElement, TrackSelect trackSelect, Palette palette, Order orderInTrackBar) {
        this.ID = NEXT_ID++;
        this.trackElement = trackElement;
        this.trackSelect = trackSelect;
        this.palette = palette;
        this.instrument = Instrument.DEFAULT;
        this.editor = new Editor(this);
        this.orderInTrackBar = orderInTrackBar;
    }

    @Override
    public int compareTo(@NotNull Track o) {
        return Order.compare(orderInTrackBar, o.orderInTrackBar);
    }
}