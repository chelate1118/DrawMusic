package com.draw.drawmusic.track;

import com.draw.drawmusic.properties.Instrument;
import com.draw.drawmusic.properties.Palette;
import com.draw.drawmusic.tools.CalculatorException;
import javafx.geometry.Insets;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;

public class TrackElement {
    private Palette    palette;
    private Instrument instrument;
    private void makeShape() throws CalculatorException {
        setColorCircle();
        setChooseInstrument();
        setGridPane();
    }

    TrackElement(Palette _palette, Instrument _instrument) throws CalculatorException {
        palette    = _palette;
        instrument = _instrument;

        makeShape();
    }


    private static final double MENU_BUTTON_WIDTH = 120.0;
    private static final double BRIGHTER_AMOUNT = 3.0;
    private Circle     colorCircle;
    private MenuButton chooseInstrument;
    private GridPane   gridPane;

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
        chooseInstrument.setPrefWidth(MENU_BUTTON_WIDTH);
        chooseInstrument.setText(instrument.getName());

        for(MenuItem x : chooseInstrument.getItems()) {
            x.setOnAction(actionEvent -> {
                try {
                    instrument = Instrument.fromName(x.getText());
                    chooseInstrument.setText(x.getText());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }

        GridPane.setHgrow(chooseInstrument, Priority.ALWAYS);
        GridPane.setMargin(chooseInstrument, new Insets(8, 0, 8, 0));
    }

    private void setGridPane() throws CalculatorException {
        gridPane = new GridPane();
        gridPane.setBackground(new Background(new BackgroundFill(palette.getBrightColor(BRIGHTER_AMOUNT, 0.4),
                new CornerRadii(13.0), Insets.EMPTY)));
        gridPane.setBorder(new Border(new BorderStroke(palette.getBrightColor(BRIGHTER_AMOUNT, 0.7), BorderStrokeStyle.SOLID,
                new CornerRadii(10.0), new BorderWidths(3.0))));
        VBox.setMargin(gridPane, new Insets(5, 5, 5, 5));

        gridPane.add(colorCircle, 0, 0, 1, 2);
        gridPane.add(chooseInstrument, 1, 0);
    }

    public GridPane getGridPane() { return gridPane; }
}
