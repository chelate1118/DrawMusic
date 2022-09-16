package com.draw.drawmusic.editor;

import com.draw.drawmusic.note.PlayableNote;
import com.draw.drawmusic.note_properties.Bars;
import com.draw.drawmusic.track.Track;

import java.util.ArrayList;

public class MidiEditor extends Editor {
    private final ArrayList<PlayableNote> noteArrayList = new ArrayList<>();
    private final ArrayList<Bars> bars = new ArrayList<>();

    public MidiEditor(Track parent) {
        this.parent = parent;
        Editor.showAllEditors();
    }

    @Override
    public void updateShape() {
//        noteArrayList.add(new PianoNote(parent));
        group.getChildren().clear();
        for(PlayableNote i : noteArrayList) {
            group.getChildren().add(i.toShape());
        }
    }
}
