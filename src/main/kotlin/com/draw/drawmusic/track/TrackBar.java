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
    private static final ArrayList<Track> trackElements = new ArrayList<>();
    public static ArrayList<Track> getTrackElements() { return trackElements; }

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

    public static void addSelected(Track element, boolean ctrlClicked, boolean shiftClicked) {
        if (shiftClicked) {
            int index = trackElements.indexOf(element), siz = trackElements.size();
            int start = -1;
            for(int i = 0; i < siz; i++) {
                if(trackElements.get(i).trackSelect == TrackSelect.currentSelected) {
                    start = i;
                }
            }
            if(start == -1) {
                element.trackSelect = TrackSelect.currentSelected;
            } else {
                for(Track _element : trackElements)
                    _element.trackSelect = TrackSelect.unSelected;
                for(int i = Integer.min(index, start); i <= Integer.max(index, start); i++) {
                    if(i == start) trackElements.get(i).trackSelect = TrackSelect.currentSelected;
                    else trackElements.get(i).trackSelect = TrackSelect.multiSelected;
                }
            }
        } else if (ctrlClicked) {
            if(element.trackSelect != TrackSelect.unSelected) {
                element.trackSelect = TrackSelect.unSelected;
            } else {
                for (Track _element : trackElements) {
                    if (_element.trackSelect == TrackSelect.currentSelected)
                        _element.trackSelect = TrackSelect.multiSelected;
                }
                element.trackSelect = TrackSelect.currentSelected;
            }
        } else {
            for (Track _element : trackElements) {
                _element.trackSelect = TrackSelect.unSelected;
            }
            element.trackSelect = TrackSelect.currentSelected;
        }
        updateElements();
    }

    public static void addElement() {
        FXMLController.Companion.fxmlLoad("track-element.fxml");

        drawElements();
        scrollPane.setVvalue(0.0);
        addSelected(trackElements.get(0), false, false);
        fadeInElement(contentBar.getChildren().get(0));

        System.out.println("[TrackBar : addElement()] New track added : " + trackElements.get(trackElements.size()-1));
    }

    public static void drawElements() {
        contentBar.getChildren().clear();
        for (Track element: trackElements) {
            contentBar.getChildren().add(element.trackElement.getGridPane());
        }
    }

    public static void updateElements() {
        for (Track element: trackElements) {
            element.trackElement.updateShape();
        }
    }

    public static void fadeInElement(Node node) {
        FadeTransition ft = new FadeTransition(Duration.millis(500), node);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
}
