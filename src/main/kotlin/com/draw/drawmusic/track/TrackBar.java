package com.draw.drawmusic.track;

import com.draw.drawmusic.properties.Instrument;
import com.draw.drawmusic.properties.Palette;
import com.draw.drawmusic.tools.CalculatorException;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TrackBar { // This class doesn't need instance. Every field and method is static.
    private static VBox contentBar, resizeBar;
    private static ScrollPane scrollPane;
    private static final ArrayList<TrackElement> elements = new ArrayList<>();

    private static void enableMouseResize() {
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        resizeBar.addEventFilter(MouseEvent.MOUSE_DRAGGED, e ->
                scrollPane.setPrefWidth(scrollPane.getWidth() + e.getX()));
    }

    public static void init(VBox _trackBarContent, ScrollPane _trackBarScrollPane, VBox _trackBarResize) {
        contentBar = _trackBarContent;
        scrollPane = _trackBarScrollPane;
        resizeBar  = _trackBarResize;

        enableMouseResize();
    }

    public static void addElement() {
        TrackElement newElement = new TrackElement(Palette.next(), Instrument.Grandpiano);
        elements.add(newElement);
    }

    public static void showElements() throws CalculatorException {
        contentBar.getChildren().clear();
        for (TrackElement element: elements) {
            contentBar.getChildren().add(element.drawInTrackToHBox());
        }
    }
}
