package com.draw.drawmusic.note_properties;

public class MidiNote {
    private static final double WHITE_KEY_HEIGHT = 30;
    private static final double BLACK_KEY_HEIGHT = WHITE_KEY_HEIGHT * 0.6;
    public static final int MIN_PITCH = 21;
    public static final int MAX_PITCH = 108;

    public static int YToPitch(double Y) {
        for(int i = MIN_PITCH; i < MAX_PITCH; i++){
            if(Y >= pitchToY(i + 1) && Y <= pitchToY(i)) return i;
        }
        return 0;
    }

    public static double pitchToMiddleY(int pitch) {
        int i = (108 - pitch) / 12;
        int j = (108 - pitch) % 12;

        return (i * 7 + new double[]{0, 1, 1.5, 2, 2.5, 3, 3.5, 4, 5, 5.5, 6, 6.5}[j] + 0.5) * WHITE_KEY_HEIGHT;
    }

    public static double pitchToY(int pitch) {
        if(isWhiteKey(pitch)) {
            return pitchToMiddleY(pitch) - WHITE_KEY_HEIGHT / 2;
        }
        return pitchToMiddleY(pitch) - BLACK_KEY_HEIGHT / 2;
    }

    public static double getNoteHeight(int pitch) {
        if(isWhiteKey(pitch)) return WHITE_KEY_HEIGHT;
        return BLACK_KEY_HEIGHT;
    }

    public static double getWhiteKeyHeight() {
        return WHITE_KEY_HEIGHT;
    }

    public static double getBlackKeyHeight() {
        return BLACK_KEY_HEIGHT;
    }

    public static boolean isWhiteKey(int pitch) {
        return new boolean[]{
                true, true, false, true, false, true, false, true, true, false, true, false
        }[(108 - pitch) % 12];
    }
}
