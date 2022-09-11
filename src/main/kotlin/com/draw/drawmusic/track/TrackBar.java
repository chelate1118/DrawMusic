package com.draw.drawmusic.track;

import com.draw.drawmusic.controllers.FXMLController;
import com.draw.drawmusic.history.TrackHistory;
import com.draw.drawmusic.tools.ArrayTool;
import com.draw.drawmusic.tools.KeyBoardTool;
import com.draw.drawmusic.tools.Order;
import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

public class TrackBar {
    public static final int TRACK_MAX_NUMBER = 50;
    private static ToolBar toolBar;
    private static VBox contentBar;
    private static ScrollPane scrollPane;
    private static Label displayTrackNumbers;
    private static Button addButton;
    private static final ArrayList<Track> trackElements = new ArrayList<>();

    public static void init(VBox _trackBarContent, ScrollPane _trackBarScrollPane, ToolBar _toolBar) {
        contentBar = _trackBarContent;
        scrollPane = _trackBarScrollPane;
        toolBar = _toolBar;

        fitScrollPaneSize();
        setToolBar();
    }

    private static void fitScrollPaneSize() {
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
    }

    private static void setToolBar() {
        matchToolBarItems();
        setToolBarEvent();
    }

    public static void matchKeyboardShortcuts() {
        KeyBoardTool.setKeyEventHandler(TrackBar::deleteSelectedElements, KeyCode.DELETE, KeyCombination.SHIFT_DOWN);
        KeyBoardTool.setKeyEventHandler(TrackBar::addButtonClicked, KeyCode.N, KeyCombination.CONTROL_DOWN);
    }

    public static void addButtonClicked() {
        loadTrackElementFXML();
        TrackHistory.saveAddHistory(ArrayTool.last(trackElements));
    }

    public static void addElement(Track newElement) {
        trackElements.add(newElement);
        syncOrderInDisplay();
        selectElements(newElement, false, false);
        displayAndUpdateElements();
        fadeInElement(newElement);
        scrollDownPossible();
        setDisplayTrackNumbers();
    }

    private static void displayAndUpdateElements() {
        displayElements();
        updateElements();
    }

    public static void selectAndUpdateElements(Track element) {
        selectElements(element, false, false);
        updateElements();
    }

    public static void selectAndUpdateElements(Track element, boolean ctrlClicked, boolean shiftClicked) {
        selectElements(element, ctrlClicked, shiftClicked);
        updateElements();
    }

    private static void deleteSelectedElements() {
        for(Track element : getSelectedList()) {
            deleteElement(element);
        }
    }

    public static void deleteElement(Track track) {
        selectAndUpdateElements(ArrayTool.closeElement(trackElements, track));
        trackElements.remove(track);
        setDisplayTrackNumbers();
        displayElements();
    }

    public static void moveUp(Track a) {
        switchTwoTracks(a, ArrayTool.previous(trackElements, a));
    }

    public static void moveDown(Track a) {
        switchTwoTracks(a, ArrayTool.next(trackElements, a));
    }

    public static Order makeLastOrder() {
        if(trackElements.isEmpty()) return Order.DEFAULT;
        return Order.nextOrderThan(ArrayTool.last(trackElements).orderInTrackBar);
    }

    private static void switchTwoTracks(Track a, Track b) {
        Order.swap(a.orderInTrackBar, b.orderInTrackBar);
        syncOrderInDisplay();
        displayElements();
    }

    private static void syncOrderInDisplay() {
        Collections.sort(trackElements);
    }

    private static void matchToolBarItems() {
        addButton = (Button) ArrayTool.first(toolBar.getItems());
        displayTrackNumbers = (Label) ArrayTool.last(toolBar.getItems());
        displayTrackNumbers.setText("0/" + TRACK_MAX_NUMBER);
    }

    private static void setToolBarEvent() {
        addButton.setOnAction(actionEvent -> addButtonClicked());
    }

    private static void loadTrackElementFXML() {
        FXMLController.Companion.fxmlLoad("midi-track.fxml");
    }

    @NotNull
    private static ArrayList<Track> getSelectedList() {
        final ArrayList<Track> selected = new ArrayList<>();
        for(Track element : trackElements) {
            if(element.trackSelect != TrackSelect.unSelected) {
                selected.add(element);
            }
        }
        return selected;
    }

    private static void setDisplayTrackNumbers() {
        displayTrackNumbers.setText(trackElements.size() + "/" + TRACK_MAX_NUMBER);
    }

    private static void displayElements() {
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

    private static void fadeInElement(@NotNull Track node) {
        FadeTransition ft = new FadeTransition(Duration.millis(500), node.trackElement.getGridPane());
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    private static void scrollDownPossible() {
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
