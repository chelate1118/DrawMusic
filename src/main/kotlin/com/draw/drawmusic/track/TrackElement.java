package com.draw.drawmusic.track;

import com.draw.drawmusic.notes.Editor;
import com.draw.drawmusic.properties.Instrument;
import com.draw.drawmusic.properties.Palette;
import com.draw.drawmusic.tools.CalculatorException;
import javafx.geometry.Insets;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TrackElement {
    private Palette    palette;
    private Instrument instrument;
    private String     trackName;
    private Editor     editor;
    TrackElement(Palette _palette, Instrument _instrument) throws CalculatorException {
        palette    = _palette;
        instrument = _instrument;
        trackName = "";

        editor = new Editor(this);
        editor.show();
        makeShape();
    }

    public Palette getPalette() {
        return palette;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    private static final double ELEMENT_WIDTH = 120.0;

    private static final double BRIGHTER_AMOUNT = 3.0;
    private Circle     colorCircle;
    private MenuButton chooseInstrument;
    private TextField  inputTrackName;
    private GridPane   gridPane;

    private void makeShape() throws CalculatorException {
        setColorCircle();
        setChooseInstrument();
        setInputTrackName();
        setGridPane();
    }

    private void setColorCircle() throws CalculatorException {
        colorCircle = new Circle(12.0, palette.getBrightColor(BRIGHTER_AMOUNT, 1.0));
        colorCircle.setStrokeWidth(3.0);
        colorCircle.setStroke(palette.color());
        GridPane.setMargin(colorCircle, new Insets(30, 30, 30, 20));
    }

    private void setChooseInstrument() throws CalculatorException {
        chooseInstrument = Instrument.makeInstrumentPicker();
        chooseInstrument.setBackground(new Background(new BackgroundFill(palette.getDarkColor(1.0, 1.0),
                new CornerRadii(2.0), Insets.EMPTY)));
        chooseInstrument.setPrefWidth(ELEMENT_WIDTH);
        chooseInstrument.setText(instrument.getName());

        for(MenuItem x : chooseInstrument.getItems()) {
            x.setOnAction(actionEvent -> {
                try {
                    instrument = Instrument.fromName(x.getText());
                    chooseInstrument.setText(x.getText());
                    inputTrackName.setText(x.getText());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }

        GridPane.setHgrow(chooseInstrument, Priority.ALWAYS);
        GridPane.setMargin(chooseInstrument, new Insets(8, 0, 8, 0));
    }

    private void setInputTrackName() throws CalculatorException {
        inputTrackName = new TextField();
        inputTrackName.textProperty().addListener((a, b, val) -> trackName = val);
        inputTrackName.setMaxWidth(ELEMENT_WIDTH);
        inputTrackName.setText(chooseInstrument.getText());
        inputTrackName.setStyle("-fx-text-fill: white;");
        inputTrackName.setBackground(new Background(new BackgroundFill(Color.hsb(0, 0, 0, 0.2),
                CornerRadii.EMPTY, Insets.EMPTY)));
        inputTrackName.setBorder(new Border(new BorderStroke(palette.getDarkColor(1.0, 1.0),
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 1.5, 0))));

        GridPane.setHgrow(inputTrackName, Priority.ALWAYS);
        GridPane.setMargin(inputTrackName, new Insets(8, 0, 8, 0));
    }

    private void setGridPane() throws CalculatorException {
        gridPane = new GridPane();
        gridPane.setBackground(new Background(new BackgroundFill(palette.getBrightColor(BRIGHTER_AMOUNT, 0.4),
                new CornerRadii(13.0), Insets.EMPTY)));
        gridPane.setBorder(new Border(new BorderStroke(palette.getBrightColor(BRIGHTER_AMOUNT, 0.7), BorderStrokeStyle.SOLID,
                new CornerRadii(10.0), new BorderWidths(3.0))));
        VBox.setMargin(gridPane, new Insets(5, 5, 5, 5));

        gridPane.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            if(e.getButton() == MouseButton.PRIMARY)
                TrackBar.addSelected(this, e.isControlDown());
        });

        gridPane.add(colorCircle, 0, 0, 1, 2);
        gridPane.add(chooseInstrument, 1, 1);
        gridPane.add(inputTrackName, 1, 0);
    }

    public GridPane getGridPane() { return gridPane; }
}
