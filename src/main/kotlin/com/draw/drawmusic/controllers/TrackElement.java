package com.draw.drawmusic.controllers;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TrackElement {
    private Color color;
    private Instrument instrument;

    TrackElement(Color _color, Instrument _instrument) {
        color      = _color;
        instrument = _instrument;
    }

    public HBox drawInTrackToHBox() {
        HBox hBox = new HBox();
        hBox.setBackground(new Background(new BackgroundFill(Color.gray(0.4),
                new CornerRadii(10), Insets.EMPTY)));
        hBox.setBorder(new Border(new BorderStroke(color.darker(), BorderStrokeStyle.SOLID,
                new CornerRadii(10), new BorderWidths(2.0))));

        hBox.getChildren().add(new Circle(20, color));
        hBox.getChildren().add(new ImageView(instrument.toIcon()));
        VBox.setMargin(hBox, new Insets(5, 5, 5, 5));

        return hBox;
    }
}
