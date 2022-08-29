package com.draw.drawmusic.track;

import com.draw.drawmusic.history.DeleteTrack;
import com.draw.drawmusic.properties.Instrument;
import com.draw.drawmusic.properties.Palette;
import com.draw.drawmusic.tools.CalculatorException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ResourceBundle;

public class TrackBarElementController implements Initializable {
    @FXML public Circle     colorCircle;
    @FXML public MenuButton chooseInstrument;
    @FXML public TextField  inputTrackName;
    @FXML public GridPane   gridPane;

    public GridPane getGridPane() {
        return gridPane;
    }
    private Track parent;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectToParent();
        makeShape();
        makeEventControl();
        TrackBar.addElement(parent);
    }

    @FXML
    public void trashButtonClicked() {
        DeleteTrack.saveNewHistory(parent);
        TrackBar.deleteElement(parent);
    }

    @FXML
    public void moveUp() {
        TrackBar.moveUp(parent);
    }

    @FXML
    public void moveDown() {
        TrackBar.moveDown(parent);
    }

    private void connectToParent() {
        parent = new Track(this, TrackSelect.unSelected, Palette.next(), TrackBar.makeLastOrder());
    }

    private void makeShape() {
        try {
            makeColorCircle();
            makeChooseInstrument();
            makeInputTrackName();
            makeGridPane();
        } catch (CalculatorException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateShape() {
        try {
            updateGridPane();
        } catch (CalculatorException e) {
            throw new RuntimeException(e);
        }
    }

    private void makeColorCircle() throws CalculatorException {
        final double BRIGHTER = 3.0;
        colorCircle.setStroke(parent.palette.color());
        colorCircle.setFill(parent.palette.brightColor(BRIGHTER));
    }

    private void makeChooseInstrument() throws CalculatorException {
        final double ALPHA = 0.5;

        chooseInstrument.setBackground(new Background(new BackgroundFill(Color.hsb(0, 0, 0, ALPHA),
                new CornerRadii(2.0), Insets.EMPTY)));
        chooseInstrument.setText(parent.instrument.getName());

        for(Instrument _instrument : Instrument.getInstrumentList()) {
            chooseInstrument.getItems().add(makeMenuItem(_instrument));
        }
    }

    private void makeInputTrackName() throws CalculatorException {
        final double DARKER = 1.0;
        final double ALPHA = 0.2;

        inputTrackName.setText("Track " + parent.ID);

        inputTrackName.setBackground(new Background(new BackgroundFill(Color.hsb(0, 0, 0, ALPHA),
                CornerRadii.EMPTY, Insets.EMPTY)));
        inputTrackName.setBorder(new Border(new BorderStroke(parent.palette.darkColor(DARKER),
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 1.5, 0))));
    }

    public void makeGridPane() throws CalculatorException {
        final double BRIGHTER = 3.0;
        final double DARKER = 0.7;
        final double ALPHA = 0.4;

        gridPane.setBackground(new Background(new BackgroundFill(parent.palette.brightColor(BRIGHTER, ALPHA),
                new CornerRadii(13.0), Insets.EMPTY)));

        gridPane.setBorder(new Border(new BorderStroke(parent.palette.darkColor(DARKER), BorderStrokeStyle.SOLID,
                new CornerRadii(10.0), new BorderWidths(3.0))));

        VBox.setMargin(gridPane, new Insets(5, 5, 5, 5));
    }

    private void makeEventControl() {
        gridPane.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if(mouseEvent.getButton() == MouseButton.PRIMARY)
                TrackBar.selectAndUpdateElements(parent, mouseEvent.isControlDown(), mouseEvent.isShiftDown());
        });
    }

    @NotNull
    private MenuItem makeMenuItem(@NotNull Instrument _instrument) {
        MenuItem menuItem = new MenuItem(_instrument.getName());
        menuItem.setOnAction(actionEvent -> {
            parent.instrument = _instrument;
            chooseInstrument.setText(_instrument.getName());
        });
        return menuItem;
    }

    private void updateGridPane() throws CalculatorException {
        final double BRIGHTER = 3.0;
        final double DARKER = 0.7;

        Paint borderColor;
        if (parent.trackSelect == TrackSelect.unSelected) {
            borderColor = parent.palette.darkColor(DARKER);
        } else if (parent.trackSelect == TrackSelect.currentSelected) {
            borderColor = parent.palette.brightColor(BRIGHTER);
        } else { // trackBarElement.trackSelect == TrackSelect.multiSelected
            borderColor = parent.palette.brightColor(BRIGHTER);
        }

        gridPane.setBorder(new Border(new BorderStroke(borderColor, BorderStrokeStyle.SOLID,
                new CornerRadii(10.0), new BorderWidths(3.0))));
    }
}
