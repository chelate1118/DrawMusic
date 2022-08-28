package com.draw.drawmusic.track;

import com.draw.drawmusic.MainApplicationManager;
import com.draw.drawmusic.controllers.FXMLController;
import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Every field and method are static
 */
public class TrackBar {
    public static final int TRACK_MAX_NUMBER = 50;
    private static ToolBar toolBar;
    private static VBox contentBar;
    private static ScrollPane scrollPane;
    private static Label displayTrackNumbers;
    private static final ArrayList<Track> trackElements = new ArrayList<>();
    public static ArrayList<Track> getTrackElements() { return trackElements; }

    public static void init(VBox _trackBarContent, ScrollPane _trackBarScrollPane, ToolBar _toolBar) {
        contentBar = _trackBarContent;
        scrollPane = _trackBarScrollPane;
        toolBar = _toolBar;

        fitSize();
        setToolBarEvent();

        System.out.println("[TrackBar : init()] TrackBar initialized");
    }

    private static void fitSize() {
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
    }

    private static void setToolBarEvent() {
        Button addButton = (Button) toolBar.getItems().get(0);
        displayTrackNumbers = (Label) toolBar.getItems().get(toolBar.getItems().size() - 1);

        displayTrackNumbers.setText("0/" + TRACK_MAX_NUMBER);

        addButton.setOnAction(actionEvent -> addElement());
    }

    public static void setKeyboardShortcuts() {
        MainApplicationManager.Companion.getScene().addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            final KeyCombination keyComb = new KeyCodeCombination(KeyCode.DELETE, KeyCombination.CONTROL_DOWN);
            if (keyComb.match(keyEvent)) {
                deleteSelected();
            }
        });
    }

    private static void setDisplayTrackNumbers() {
        displayTrackNumbers.setText(trackElements.size() + "/" + TRACK_MAX_NUMBER);
    }

    public static void addSelected(Track element, boolean ctrlClicked, boolean shiftClicked) {
        selectElements(element, ctrlClicked, shiftClicked);
        updateElements();
    }

    private static void addElement() {
        if(trackElements.size() >= TRACK_MAX_NUMBER) {
            System.out.println("[TrackBar : addElement()] Failed : Too many tracks");
            return;
        }

        FXMLController.Companion.fxmlLoad("track-element.fxml");
        int insertIndex = trackElements.size() - 1;

        drawElements();
        addSelected(trackElements.get(insertIndex), false, false);
        fadeElement(insertIndex);
        scrollDown();
        setDisplayTrackNumbers();

        System.out.println("[TrackBar : addElement()] New track added : " + trackElements.get(insertIndex));
    }

    private static void deleteSelected() {
        final ArrayList<Track> selected = new ArrayList<>();
        for(Track element : trackElements) {
            if(element.trackSelect != TrackSelect.unSelected) {
                selected.add(element);
            }
        }
        for(Track element : selected) {
            deleteElement(element);
        }
    }

    public static void deleteElement(Track track) {
        trackElements.remove(track);
        setDisplayTrackNumbers();
        drawElements();
    }

    private static void drawElements() {
        contentBar.getChildren().clear();
        for (Track element: trackElements) {
            contentBar.getChildren().add(element.trackElement.getGridPane());
        }
    }

    private static void updateElements() {
        for (Track element: trackElements) {
            element.trackElement.updateShape();
        }
    }

    private static void fadeElement(int node) {
        FadeTransition ft = new FadeTransition(Duration.millis(500), contentBar.getChildren().get(node));
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    private static void scrollDown() {
        scrollPane.setVvalue(scrollPane.getVmax());
    }

    private static void selectElements(Track element, boolean ctrlClicked, boolean shiftClicked) {
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
    }
}
