package com.draw.drawmusic.notes;

import com.draw.drawmusic.times.Bars;
import com.draw.drawmusic.track.Track;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ArrayList;

public class Editor {
    private static StackPane stackPane;

    public static void init(StackPane _stackPane) {
        stackPane = _stackPane;
    }

    private final Track parent;
    private final Group group = new Group();

    public Editor(Track parent) {
        this.parent = parent;
        stackPane.getChildren().add(group);
        StackPane.setAlignment(group, Pos.TOP_LEFT);
    }

    private final ArrayList<MusicNote> display = new ArrayList<>();
    private final ArrayList<PlayableNote> playableNotes = new ArrayList<>();
    private final ArrayList<Bars>      bars = new ArrayList<>();

    public void show() {
        display.add(new PianoNote(parent));
        for(MusicNote i : display) {
            group.getChildren().add(i.toShape(NoteSelect.NoteSelected));
        }
    }

    public void playList() {
        try {
            Play.playList(playableNotes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateShape() {

    }
}
