package com.draw.drawmusic.properties;

import javafx.scene.control.RadioMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class Instrument {
    private final int number;
    private Instrument(int _num) { number = _num; }
    public static Instrument Grandpiano = new Instrument(0);
    private static ArrayList<Instrument> Instruments = new ArrayList<>(
            Arrays.asList(Instrument.Grandpiano)
    );

    public static ArrayList<Instrument> getInstruments() {
        return Instruments;
    }

    private static final ArrayList<String> instrumentName = new ArrayList<>(
            Arrays.asList("Grand Piano")
    );

    public String getName() { return instrumentName.get(number); }

    public ImageView toIconImageView() {
        return new ImageView(new Image(ResourcePaths.getAbsolutePath(
                "images", instrumentName.get(number) + ".png")));
    }

    public ImageView toIconImageView(double size) {
        ImageView imageView = this.toIconImageView();
        imageView.setFitWidth(20.0);
        imageView.setFitHeight(20.0);
        return imageView;
    }

    private static ArrayList<RadioMenuItem> instrumentPicker = makeInstrumentPicker();

    private static ArrayList<RadioMenuItem> makeInstrumentPicker() {
        ArrayList<RadioMenuItem> val = new ArrayList<>();



        return val;
    }
}
