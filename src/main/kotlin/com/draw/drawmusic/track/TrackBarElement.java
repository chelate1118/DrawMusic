package com.draw.drawmusic.track;

import com.draw.drawmusic.notes.Editor;

public class TrackBarElement {
    public TrackBarElementController trackElement;
    public TrackSelect trackSelect;
    public Editor editor;

    public TrackBarElement(TrackBarElementController _element, TrackSelect _trackSelect) {
        trackElement = _element;
        trackSelect = _trackSelect;
        editor = new Editor(this);
    }
}