package com.draw.drawmusic.note;

import com.draw.drawmusic.note_properties.MidiNote;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class WhiteKey extends ShapeNote {
    private static final double NOTE_HEIGHT = MidiNote.getWhiteKeyHeight();

    protected WhiteKey(int pitch) {
        super();
        this.pitch = pitch;
    }

    @Override
    public Rectangle toShape() {
        Rectangle rect = super.toShape();

        rect.setHeight(NOTE_HEIGHT);
        rect.setWidth(NOTE_WIDTH);
        rect.setFill(Color.WHITE);
        rect.setY(rect.getY() - NOTE_HEIGHT / 2);

        return rect;
    }

    public static double getNoteWidth() {
        return NOTE_WIDTH;
    }
}
