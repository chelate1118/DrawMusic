package com.draw.drawmusic.note;

import com.draw.drawmusic.note_properties.MidiNote;
import javafx.scene.shape.Rectangle;

public class BlackKey extends ShapeNote {
    private static final double NOTE_WIDTH = 105;
    private static final double NOTE_HEIGHT = MidiNote.NOTE_HEIGHT * 0.6;

    protected BlackKey(int pitch) {
        this.pitch = pitch;
    }

    @Override
    public Rectangle toShape() {
        Rectangle rect = super.toShape();
        rect.setHeight(NOTE_HEIGHT);
        rect.setWidth(NOTE_WIDTH);
        rect.setY(rect.getY() - NOTE_HEIGHT / 2);
        return rect;
    }
}
