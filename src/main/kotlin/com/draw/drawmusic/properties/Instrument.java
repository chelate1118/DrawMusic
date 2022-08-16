package com.draw.drawmusic.properties;

import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioMenuItem;

import java.util.ArrayList;
import java.util.Arrays;

public class Instrument {
    private final int index;
    private static int count = 1; // number of instruments
    private Instrument(int _num) { index = _num; }
    public static Instrument Grandpiano = new Instrument(0);

    private static final ArrayList<String> instrumentName = new ArrayList<>(Arrays.asList("Grand Piano"));

    public String getName() { return instrumentName.get(index); }

    public void addInstrument() {

    }

    public static MenuButton makeInstrumentPicker() {
        MenuButton val = new MenuButton();

        for(int i = 0; i < count; i++) {
            val.getItems().add(new RadioMenuItem(instrumentName.get(i)));
        }

        return val;
    }
}
