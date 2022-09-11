package com.draw.drawmusic.note;

import com.draw.drawmusic.note_properties.MidiNote;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class ShapeNote extends MusicNote{
    protected static final double NOTE_WIDTH = 150;
    public static ShapeNote fromPitch(int pitch) {
        if (MidiNote.isWhiteKey(pitch)) {
            return new WhiteKey(pitch);
        } else {
            return new BlackKey(pitch);
        }
    }

    @Override
    public Rectangle toShape() {
        Rectangle rect = new Rectangle();
        final double STROKE_WIDTH = 2.5;
        final double ARC_RADIUS = 10.0;

        rect.setStroke(Color.BLACK);
        rect.setY(MidiNote.pitchToMiddleY(pitch));
        rect.setStrokeWidth(STROKE_WIDTH);
        rect.setArcHeight(ARC_RADIUS);
        rect.setArcWidth(ARC_RADIUS);

        return rect;
    }
}
