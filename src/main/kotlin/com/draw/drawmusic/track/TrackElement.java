package com.draw.drawmusic.track;

import com.draw.drawmusic.properties.Instrument;
import com.draw.drawmusic.properties.Palette;
import com.draw.drawmusic.tools.CalculatorException;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;

public class TrackElement {
    private Palette palette;
    private Instrument instrument;

    TrackElement(Palette _palette, Instrument _instrument) {
        palette    = _palette;
        instrument = _instrument;
    }

    public void setInstrument(Instrument instrument) { this.instrument = instrument; }

    public void setPalette(Palette palette) { this.palette = palette; }

    public HBox drawInTrackToHBox() throws CalculatorException {
        HBox hBox = new HBox();
        hBox.setBackground(new Background(new BackgroundFill(palette.getBrightColor(3.0),
                new CornerRadii(8.0), Insets.EMPTY)));
        hBox.setBorder(new Border(new BorderStroke(palette.getColor(), BorderStrokeStyle.SOLID,
                new CornerRadii(5.0), new BorderWidths(3.0))));

        hBox.getChildren().add(new Circle(20, palette.getColor()));
        hBox.getChildren().add(new ImageView(instrument.toIcon()));
        VBox.setMargin(hBox, new Insets(5, 5, 5, 5));

        return hBox;
    }
}
