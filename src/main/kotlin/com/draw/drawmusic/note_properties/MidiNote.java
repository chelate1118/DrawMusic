package com.draw.drawmusic.note_properties;

public class MidiNote {
    public static double NOTE_HEIGHT = 30;
    public static final int MIN_PITCH = 21;
    public static final int MAX_PITCH = 108;

    public static double pitchToY(int pitch) {
        int i = (108 - pitch) / 12;
        int j = (108 - pitch) % 12;

        return (i * 7 + new double[]{0, 1, 1.5, 2, 2.5, 3, 3.5, 4, 5, 5.5, 6, 6.5}[j] + 0.5) * NOTE_HEIGHT;
    }

    public static boolean isWhiteKey(int pitch) {
        return new boolean[]{
                true, true, false, true, false, true, false, true, true, false, true, false
        }[(108 - pitch) % 12];
    }
}
