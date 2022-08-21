package com.draw.drawmusic.track;

import com.draw.drawmusic.properties.Instrument;
import com.draw.drawmusic.properties.Palette;
import com.draw.drawmusic.tools.CalculatorException;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;

public class TrackBar { // This class does not require any instances. Every field and method is static.
    private static VBox contentBar;
    private static ScrollPane scrollPane;
    private static final ArrayList<TrackElement> elements = new ArrayList<>();
    private static final ArrayList<Integer> selected = new ArrayList<>();

    private static void fitSize() {
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
    }

    public static void init(VBox _trackBarContent, ScrollPane _trackBarScrollPane) {
        contentBar = _trackBarContent;
        scrollPane = _trackBarScrollPane;

        fitSize();
    }

    public static void addSelected(TrackElement element, boolean ctrlClicked) {
        if(!ctrlClicked) {
            selected.clear();
            selected.trimToSize();
        }

        selected.add(elements.indexOf(element));
        System.out.println("Track bar element has selected : " + selected);
    }

    public static void addElement() throws CalculatorException {
        TrackElement newElement = new TrackElement(Palette.next(), Instrument.Grandpiano);
        elements.add(newElement);

        showElements();
        addSelected(newElement, false);
        fadeInElement(contentBar.getChildren().get(elements.size() - 1));

        System.out.println("New track bar element added : " + newElement);
    }

    public static void showElements() {
        contentBar.getChildren().clear();
        for (TrackElement element: elements) {
            contentBar.getChildren().add(element.getGridPane());
        }
    }

    public static void fadeInElement(Node node) {
        FadeTransition ft = new FadeTransition(Duration.millis(500), node);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
}
