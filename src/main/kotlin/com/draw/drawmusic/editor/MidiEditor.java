package com.draw.drawmusic.editor;

import com.draw.drawmusic.note.PianoNote;
import com.draw.drawmusic.note.PlayableNote;
import com.draw.drawmusic.note_properties.Bars;
import com.draw.drawmusic.note_properties.MidiNote;
import com.draw.drawmusic.track.Track;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class MidiEditor extends Editor {
    private final ArrayList<PlayableNote> noteArrayList = new ArrayList<>();
    private final ArrayList<Bars> bars = new ArrayList<>();

    public MidiEditor(Track parent) {
        this.parent = parent;
        Editor.showAllEditors();
        drawNoteByMouse();
    }

    @Override
    public void updateShape() {
//        noteArrayList.add(new PianoNote(parent));
        group.getChildren().clear();
        for(PlayableNote i : noteArrayList) {
            group.getChildren().add(i.toShape());
        }
    }

    private void drawNoteByMouse() {
        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            PianoNote note = new PianoNote(parent, mouseEvent.getX(), mouseEvent.getX() + 100, MidiNote.YToPitch(mouseEvent.getY()));
            noteArrayList.add(note);
            updateShape();
        });
    }
}
