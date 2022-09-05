package com.draw.drawmusic.editor;

import com.draw.drawmusic.note.BlackKey;
import com.draw.drawmusic.note.ShapeNote;
import com.draw.drawmusic.note.WhiteKey;
import com.draw.drawmusic.note_properties.MidiNote;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;

import java.util.ArrayList;

public class KeyboardEditor {
    private static ScrollPane keyboardScrollPane;
    private static final Group group = new Group();
    private static final ArrayList<ShapeNote> shapeNotes = new ArrayList<>();

    public static void init(ScrollPane _keyboardScrollPane) {
        keyboardScrollPane = _keyboardScrollPane;
        keyboardScrollPane.setContent(group);

        hideScrollbar();
        setShapeNotes();
        showShapeNotes();
    }

    private static void hideScrollbar() {
        keyboardScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        keyboardScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    private static void setShapeNotes() {
        for (int pitch = MidiNote.MIN_PITCH; pitch <= MidiNote.MAX_PITCH; pitch++) {
            shapeNotes.add(ShapeNote.fromPitch(pitch));
        }
    }

    private static void showShapeNotes() {
        group.getChildren().clear();

        for (ShapeNote note : shapeNotes) {
            if(note instanceof WhiteKey)
                group.getChildren().add(note.toShape());
        }

        for (ShapeNote note : shapeNotes) {
            if(note instanceof BlackKey)
                group.getChildren().add(note.toShape());
        }
    }
}
