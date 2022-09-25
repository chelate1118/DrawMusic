package com.draw.drawmusic.track_properties;

import java.util.ArrayList;
import java.util.Arrays;

public class Instrument {
    private final int index;
    private Instrument(int _num) { index = _num; }
    public static Instrument GRANDPIANO = new Instrument(0);
    public static Instrument VIOLIN = new Instrument(1);
    public static Instrument FLUTE = new Instrument(2);
    public static Instrument DEFAULT = GRANDPIANO;

    private static final ArrayList<Instrument> instrumentList = new ArrayList<>(
            Arrays.asList(GRANDPIANO, VIOLIN, FLUTE));
    private static final ArrayList<String> instrumentName = new ArrayList<>(
            Arrays.asList("Grand Piano", "Violin", "Flute"));

    public String getName() { return instrumentName.get(index); }
    public static ArrayList<Instrument> getInstrumentList() { return instrumentList; }
}
