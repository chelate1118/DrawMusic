package com.draw.drawmusic.notes;

import com.draw.drawmusic.track.TrackElement;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class Editor {
    private static StackPane stackPane;

    public static void init(StackPane _stackPane) {
        stackPane = _stackPane;
    }

    private TrackElement element;
    public Editor(TrackElement trackElement) {
        element = trackElement;
        stackPane.getChildren().add(group);
        StackPane.setAlignment(group, Pos.TOP_LEFT);
    }

    private final Group group = new Group();
    private final ArrayList<MusicNote> notes = new ArrayList<>();

    public void show() {
        notes.add(new PianoNote());
        for(MusicNote i : notes) {
            group.getChildren().add(i.toShape());
        }
    }
}
