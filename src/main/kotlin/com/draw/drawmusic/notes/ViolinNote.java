package com.draw.drawmusic.notes;

import javafx.scene.shape.Rectangle;

public class ViolinNote extends PlayNote {
    public ViolinNote(Editor _parent) {
        super(_parent);
    }

    public PianoNote toPianoNote() {
        return null;
    }
    @Override
    public Rectangle toShape(NoteSelect isSelected) {
        return null;
    }
}
