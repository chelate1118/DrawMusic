package com.draw.drawmusic.track;

import com.draw.drawmusic.notes.Editor;

public class TrackBarElement {
    TrackBarElementController trackElement;
    Editor editor;
    TrackSelect trackSelect;

    TrackBarElement(TrackBarElementController _element, Editor _editor, TrackSelect _trackSelect) {
        trackElement = _element;
        editor = _editor;
        trackSelect = _trackSelect;
    }
}