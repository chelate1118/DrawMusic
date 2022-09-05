package com.draw.drawmusic.editor;

import com.draw.drawmusic.note.MusicNote;
import com.draw.drawmusic.note.PianoNote;
import com.draw.drawmusic.note.Play;
import com.draw.drawmusic.note.PlayableNote;
import com.draw.drawmusic.note_properties.Bars;

import java.io.IOException;
import java.util.ArrayList;

public class MidiEditor extends Editor {
    private final ArrayList<PlayableNote> noteArrayList = new ArrayList<>();
    private final ArrayList<Bars> bars = new ArrayList<>();

    public MidiEditor() {

    }

    @Override
    public void show() {
        noteArrayList.add(new PianoNote(parent));
        for(MusicNote i : noteArrayList) {
            group.getChildren().add(i.toShape());
        }
    }

    public void playAll() {
        try {
            Play.playAll(noteArrayList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateShape() {

    }
}
