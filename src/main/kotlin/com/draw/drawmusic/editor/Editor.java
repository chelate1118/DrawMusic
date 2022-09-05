package com.draw.drawmusic.editor;

import com.draw.drawmusic.track.Track;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public abstract class Editor {
    protected static StackPane stackPane;
    protected Track parent;
    protected final Group group = new Group();

    protected Editor() {
        Pane pane = new Pane();
        stackPane.getChildren().add(pane);
        pane.getChildren().add(group);
    }

    public abstract void show();

    public void setParent(Track parent) {
        this.parent = parent;
    }

    public static void init(StackPane _stackPane) {
        stackPane = _stackPane;
    }
}
