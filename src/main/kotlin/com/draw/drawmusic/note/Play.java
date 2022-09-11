package com.draw.drawmusic.note;

import com.draw.drawmusic.note_properties.Ticks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Play {
    public static void playOne(PlayableNote note) throws IOException {
        play(note.ID);
    }

    public static void playAll(ArrayList<PlayableNote> notes) throws IOException {
        Collections.sort(notes);

        new Thread(() -> {
            int i = 0, siz = notes.size();
            Ticks current;
            for(i = 0; i < siz - 1; i++) {
                try {
                    Thread.sleep(0);
                    playOne(notes.get(i));
                } catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public static native void play(int ID);
}
