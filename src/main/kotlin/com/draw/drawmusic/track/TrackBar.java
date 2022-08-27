package com.draw.drawmusic.track;

import com.draw.drawmusic.controllers.FXMLController;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Every field and method are static
 */
public class TrackBar {
    private static VBox contentBar;
    private static ScrollPane scrollPane;
    private static final ArrayList<TrackBarElement> trackElements = new ArrayList<>();
    public static ArrayList<TrackBarElement> getTrackElements() { return trackElements; }

    private static void fitSize() {
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
    }

    public static void init(VBox _trackBarContent, ScrollPane _trackBarScrollPane) {
        contentBar = _trackBarContent;
        scrollPane = _trackBarScrollPane;

        fitSize();

        System.out.println("[TrackBar : init()] TrackBar initialized");
    }

    public static void addSelected(TrackBarElement element, boolean ctrlClicked) {
        if (ctrlClicked) {
            for (TrackBarElement _element : trackElements) {
                if(_element.trackSelect == TrackSelect.currentSelected)
                    _element.trackSelect = TrackSelect.multiSelected;
            }
        } else {
            for (TrackBarElement _element : trackElements) {
                _element.trackSelect = TrackSelect.unSelected;
            }
        }
        element.trackSelect = TrackSelect.currentSelected;
    }

    public static void addElement() {
        FXMLController.Companion.fxmlLoad("track-element.fxml");

        drawElements();
        fadeInElement(contentBar.getChildren().get(trackElements.size() - 1));

        System.out.println("[TrackBar : addElement()] New track added : " + trackElements.get(trackElements.size()-1));
    }

    public static void drawElements() {
        contentBar.getChildren().clear();
        for (TrackBarElement element: trackElements) {
            contentBar.getChildren().add(element.trackElement.getGridPane());
        }
    }

    public static void fadeInElement(Node node) {
        FadeTransition ft = new FadeTransition(Duration.millis(500), node);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
}
