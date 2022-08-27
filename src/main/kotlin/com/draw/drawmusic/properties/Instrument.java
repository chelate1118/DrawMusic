package com.draw.drawmusic.properties;

import com.draw.drawmusic.notes.PianoNote;
import com.draw.drawmusic.notes.PlayNote;
import com.draw.drawmusic.notes.ViolinNote;
import com.draw.drawmusic.track.Track;

import java.util.ArrayList;
import java.util.Arrays;

public class Instrument {
    private final int index;
    private Instrument(int _num) { index = _num; }
    private static final int count = 3; // number of instruments
    public static Instrument GRANDPIANO = new Instrument(0);
    public static Instrument VIOLIN = new Instrument(1);
    public static Instrument FLUTE = new Instrument(2);

    private static final ArrayList<Instrument> instrumentList = new ArrayList<>(
            Arrays.asList(GRANDPIANO, VIOLIN, FLUTE));
    private static final ArrayList<String> instrumentName = new ArrayList<>(
            Arrays.asList("Grand Piano", "Violin", "Flute"));

    public String getName() { return instrumentName.get(index); }
    public static ArrayList<Instrument> getInstrumentList() { return instrumentList; }

    public PlayNote makeNote(Track parent) {
        switch(index) {
            case 1:
                return new PianoNote(parent);
            default:
                return new ViolinNote(parent);
        }
    }

    public static Instrument fromName(String name) throws Exception {
        for(int i = 0; i < count; i++) {
            if(instrumentName.get(i).equals(name)) return instrumentList.get(i);
        }
        throw new Exception();
    }

    public boolean equals(Instrument obj) {
        return index == obj.index;
    }
}
