package com.draw.drawmusic.editor;

import com.draw.drawmusic.track.Track;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Editor implements Comparable<Editor>{
    private static StackPane stackPane;
    private static final ArrayList<Editor> editors = new ArrayList<>();
    protected Track parent;
    protected final Group group = new Group();
    protected final Pane pane = new Pane(group);

    public static void init(StackPane _stackPane) {
        stackPane = _stackPane;
        stackPane.setPrefHeight(KeyboardEditor.getHeight());
        stackPane.setMaxHeight(KeyboardEditor.getHeight());
        stackPane.setMinHeight(KeyboardEditor.getHeight());
    }

    public static void showAllEditors() {
        Collections.sort(editors);
        stackPane.getChildren().clear();

        for(Editor editor : editors) {
            editor.updateShape();
            stackPane.getChildren().add(editor.pane);
        }
    }

    protected Editor() {
        editors.add(this);
    }

    public abstract void updateShape();

    @Override
    public int compareTo(Editor o) {
        return parent.getTrackSelect().compareTo(o.parent.getTrackSelect());
    }
}
