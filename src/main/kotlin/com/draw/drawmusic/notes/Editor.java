package com.draw.drawmusic.notes;

import com.draw.drawmusic.times.Bars;
import com.draw.drawmusic.track.TrackElement;
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

    private final TrackElement element;
    public Editor(TrackElement trackElement) {
        element = trackElement;
        stackPane.getChildren().add(group);
        StackPane.setAlignment(group, Pos.TOP_LEFT);
    }

    private final Group group = new Group();
    private final ArrayList<MusicNote> display = new ArrayList<>();
    private final ArrayList<PlayNote>  playNotes = new ArrayList<>();
    private final ArrayList<Bars>      bars = new ArrayList<>();

    public void show() {
        display.add(new PianoNote());
        display.get(0).setPalette(element.getPalette());
        for(MusicNote i : display) {
            group.getChildren().add(i.toShape(true));
        }
    }

    public void playList() throws IOException {
        Play.playList(playNotes);
    }
}
