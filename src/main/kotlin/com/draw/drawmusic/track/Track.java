package com.draw.drawmusic.track;

import com.draw.drawmusic.editor.Editor;
import com.draw.drawmusic.tools.Order;
import com.draw.drawmusic.track_properties.Palette;
import org.jetbrains.annotations.NotNull;

public abstract class Track implements Comparable<Track>{
    private static int NEXT_ID = 1;
    public TrackController trackElement;
    public TrackSelect trackSelect;
    public Editor editor;
    public Palette palette;
    public int ID;
    public Order orderInTrackBar;

    protected Track(TrackController trackElement, TrackSelect trackSelect, Palette palette, Editor editor, Order orderInTrackBar) {
        this.ID = NEXT_ID++;
        this.trackElement = trackElement;
        this.trackSelect = trackSelect;
        this.palette = palette;
        this.editor = editor;
        this.orderInTrackBar = orderInTrackBar;
    }

    @Override
    public int compareTo(@NotNull Track o) {
        return Order.compare(orderInTrackBar, o.orderInTrackBar);
    }
}