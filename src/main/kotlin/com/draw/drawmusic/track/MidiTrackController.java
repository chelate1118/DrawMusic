package com.draw.drawmusic.track;

import com.draw.drawmusic.track_properties.Instrument;
import com.draw.drawmusic.track_properties.Palette;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ResourceBundle;

public class MidiTrackController extends TrackController implements Initializable {
    @FXML public MenuButton chooseInstrument;

    protected MIDITrack parent;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectToParent();
        makeShape();
        makeEventControl();
        TrackBar.addElement(parent);
    }

    protected void connectToParent() {
        parent = new MIDITrack(this, TrackSelect.UnSelected, Palette.next(), TrackBar.makeLastOrder());
        super.parent = parent;
    }

    private void makeShape() {
        makeColorCircle();
        makeChooseInstrument();
        makeInputTrackName();
        makeGridPane();
    }

    @Override
    public void updateShape() {
        updateGridPane();
    }

    private void makeChooseInstrument() {
        final double ALPHA = 0.5;

        chooseInstrument.setBackground(new Background(new BackgroundFill(Color.hsb(0, 0, 0, ALPHA),
                new CornerRadii(2.0), Insets.EMPTY)));
        chooseInstrument.setText(parent.getInstrument().getName());

        for(Instrument _instrument : Instrument.getInstrumentList()) {
            chooseInstrument.getItems().add(makeMenuItem(_instrument));
        }
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
            parent.setInstrument(_instrument);
            chooseInstrument.setText(_instrument.getName());
        });
        return menuItem;
    }
}
