package com.draw.drawmusic;

import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class TrackBar {
    private static ScrollPane trackBarScrollPane;
    private static VBox trackBarResize;
    private static VBox trackBar;

    public static void init(ScrollPane _trackBarScrollPane, VBox _trackBar, VBox _trackBarResize) {
        trackBarScrollPane = _trackBarScrollPane;
        trackBar           = _trackBar;
        trackBarResize     = _trackBarResize;

        manageSize();
        enableMouseResize();
    }

    private static double getTrackBarHeight() {
        return 0.0;
        // TODO : Track Bar 내부 성분에 따른 Height 를 구해야 함
    }

    public static void manageSize() {
        trackBarScrollPane.setPrefHeight(MainApplicationManager.Companion.getWindowHeight());
        trackBar.setPrefHeight(Math.max(MainApplicationManager.Companion.getWindowHeight(), getTrackBarHeight()));
    }

    private static void enableMouseResize() {
        trackBarScrollPane.setFitToWidth(true);

        trackBarResize.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {
            trackBarScrollPane.setPrefWidth(trackBarScrollPane.getWidth() + e.getX());
        });
    }
}
