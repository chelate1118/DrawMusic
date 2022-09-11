package com.draw.drawmusic.track;

import com.draw.drawmusic.history.TrackHistory;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public abstract class TrackController {
    @FXML public Circle colorCircle;
    @FXML public TextField inputTrackName;
    @FXML public GridPane gridPane;

    protected Track parent;

    public GridPane getGridPane() {
        return gridPane;
    }

    protected abstract void updateShape();

    protected abstract void connectToParent();

    protected void makeColorCircle()  {
        final double BRIGHTER = 3.0;
        colorCircle.setStroke(parent.palette.color());
        colorCircle.setFill(parent.palette.brightColor(BRIGHTER));
    }

    protected void makeInputTrackName() {
        final double DARKER = 1.0;
        final double ALPHA = 0.2;

        inputTrackName.setText("Track " + parent.ID);

        inputTrackName.setBackground(new Background(new BackgroundFill(Color.hsb(0, 0, 0, ALPHA),
                CornerRadii.EMPTY, Insets.EMPTY)));
        inputTrackName.setBorder(new Border(new BorderStroke(parent.palette.darkColor(DARKER),
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 1.5, 0))));
    }

    protected void makeGridPane() {
        final double BRIGHTER = 3.0;
        final double DARKER = 0.7;
        final double ALPHA = 0.4;

        gridPane.setBackground(new Background(new BackgroundFill(parent.palette.brightColor(BRIGHTER, ALPHA),
                new CornerRadii(13.0), Insets.EMPTY)));

        gridPane.setBorder(new Border(new BorderStroke(parent.palette.darkColor(DARKER), BorderStrokeStyle.SOLID,
                new CornerRadii(10.0), new BorderWidths(3.0))));

        VBox.setMargin(gridPane, new Insets(5, 5, 5, 5));
    }

    protected void updateGridPane() {
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

    @FXML
    public void moveUp() {
        TrackHistory.saveMoveUpHistory(parent);
        TrackBar.moveUp(parent);
    }

    @FXML
    public void moveDown() {
        TrackHistory.saveMoveDownHistory(parent);
        TrackBar.moveDown(parent);
    }

    @FXML
    public void trashButtonClicked() {
        TrackHistory.saveDeleteHistory(parent);
        TrackBar.deleteElement(parent);
    }
}
