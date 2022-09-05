package com.draw.drawmusic.note;

import com.draw.drawmusic.note_properties.MidiNote;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class ShapeNote extends MusicNote{
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
        rect.setStroke(Color.BLACK);
        rect.setY(MidiNote.pitchToY(pitch));
        rect.setStrokeWidth(3.0);
        rect.setArcHeight(10);
        rect.setArcWidth(10);
        return rect;
    }
}
