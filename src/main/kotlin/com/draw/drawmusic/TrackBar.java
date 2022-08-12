package com.draw.drawmusic;

import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class TrackBar {
    private static VBox trackBarContent, trackBarResize;
    private static ScrollPane trackBarScrollPane;

    private static double getTrackBarHeight() {
        return 700.0;
        // TODO : Track Bar Element 에 따른 Height 를 구해야 함
    }

    private static void setTrackBarHeight() {
        trackBarScrollPane.heightProperty().addListener((_obs, _oldVal, newVal) -> {
            trackBarContent.setPrefHeight(Math.max(getTrackBarHeight(), newVal.doubleValue()));
            System.out.println(Math.max(getTrackBarHeight(), newVal.doubleValue()));
        });
    }

    private static void enableMouseResize() {
        trackBarScrollPane.setFitToWidth(true);

        trackBarResize.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {
            trackBarScrollPane.setPrefWidth(trackBarScrollPane.getWidth() + e.getX());
        });
    }

    public static void init(VBox _trackBarContent, ScrollPane _trackBarScrollPane, VBox _trackBarResize) {
        trackBarContent    = _trackBarContent;
        trackBarScrollPane = _trackBarScrollPane;
        trackBarResize     = _trackBarResize;

        setTrackBarHeight();
        enableMouseResize();
    }
}
