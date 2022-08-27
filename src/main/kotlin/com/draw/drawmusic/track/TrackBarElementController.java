package com.draw.drawmusic.track;

import com.draw.drawmusic.notes.Editor;
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
import javafx.scene.shape.Circle;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ResourceBundle;

public class TrackBarElementController implements Initializable {
    @FXML public Circle     colorCircle;
    @FXML public MenuButton chooseInstrument;
    @FXML public TextField  inputTrackName;
    @FXML public GridPane   gridPane;

    public GridPane getGridPane() { return gridPane; }

    private Palette    palette;
    private Instrument instrument;
    private TrackBarElement trackBarElement;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        makeShape();
    }

    private void init() {
        palette = Palette.next();
        instrument = Instrument.GRANDPIANO;
        trackBarElement = new TrackBarElement(this, new Editor(this), TrackSelect.currentSelected);
        TrackBar.getTrackElements().add(trackBarElement);
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

    private void makeColorCircle() throws CalculatorException {
        final double BRIGHTER = 3.0;
        colorCircle.setStroke(palette.color());
        colorCircle.setFill(palette.getBrightColor(BRIGHTER));
    }

    private void makeChooseInstrument() throws CalculatorException {
        final double ALPHA = 0.5;

        chooseInstrument.setBackground(new Background(new BackgroundFill(Color.hsb(0, 0, 0, ALPHA),
                new CornerRadii(2.0), Insets.EMPTY)));
        chooseInstrument.setText(instrument.getName());

        for(Instrument _instrument : Instrument.getInstrumentList()) {
            chooseInstrument.getItems().add(makeMenuItem(_instrument));
        }
    }

    private void makeInputTrackName() throws CalculatorException {
        final double DARKER = 1.0;
        final double ALPHA = 0.2;

        inputTrackName.setText(instrument.getName());

        inputTrackName.setBackground(new Background(new BackgroundFill(Color.hsb(0, 0, 0, ALPHA),
                CornerRadii.EMPTY, Insets.EMPTY)));
        inputTrackName.setBorder(new Border(new BorderStroke(palette.getDarkColor(DARKER),
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 1.5, 0))));
    }

    public void makeGridPane() throws CalculatorException {
        final double BRIGHTER = 3.0;
        final double ALPHA = 0.4;

        gridPane.setBackground(new Background(new BackgroundFill(palette.getBrightColor(BRIGHTER, ALPHA),
                new CornerRadii(13.0), Insets.EMPTY)));
        gridPane.setBorder(new Border(new BorderStroke(palette.getBrightColor(BRIGHTER), BorderStrokeStyle.SOLID,
                new CornerRadii(10.0), new BorderWidths(3.0))));
        VBox.setMargin(gridPane, new Insets(5, 5, 5, 5));

        gridPane.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if(mouseEvent.getButton() == MouseButton.PRIMARY)
                TrackBar.addSelected(trackBarElement, mouseEvent.isControlDown());
        });
    }

    @NotNull
    private MenuItem makeMenuItem(@NotNull Instrument _instrument) {
        MenuItem menuItem = new MenuItem(_instrument.getName());
        menuItem.setOnAction(actionEvent -> {
            instrument = _instrument;
            chooseInstrument.setText(_instrument.getName());
            inputTrackName.setText(_instrument.getName());
        });
        return menuItem;
    }

    public Palette getPalette() { return palette; }
}
