package com.draw.drawmusic.properties;

import com.draw.drawmusic.notes.Editor;
import com.draw.drawmusic.notes.PianoNote;
import com.draw.drawmusic.notes.PlayNote;
import com.draw.drawmusic.notes.ViolinNote;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;

public class Instrument {
    private final int index;
    private static int count = 3; // number of instruments
    private Instrument(int _num) { index = _num; }
    public static Instrument Grandpiano = new Instrument(0);

    private static final ArrayList<String> instrumentName = new ArrayList<>(
            Arrays.asList("Grand Piano", "Violin", "Flute")); // Please change 'count' when you change this array list

    public String getName() { return instrumentName.get(index); }

    public void addInstrument() {

    }

    public PlayNote makeNote(Editor parent) {
        switch(index) {
            case 1:
                return new PianoNote(parent);
            default:
                return new ViolinNote(parent);
        }
    }

    public static MenuButton makeInstrumentPicker() {
        MenuButton val = new MenuButton();

        for(int i = 0; i < count; i++) {
            val.getItems().add(new MenuItem(instrumentName.get(i)));
        }

        return val;
    }

    public static Instrument fromName(String name) throws Exception {
        for(int i = 0; i < count; i++) {
            if(instrumentName.get(i).equals(name)) return new Instrument(i);
        }
        throw new Exception();
    }

    public boolean equals(Instrument obj) {
        return index == obj.index;
    }
}
