package com.draw.drawmusic.note;

import com.draw.drawmusic.tools.InteractBin;
import com.draw.drawmusic.note_properties.Ticks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Play {
    public static void playOne(PlayableNote note) throws IOException {
        InteractBin.sendCommand("s p\n" + note.ID + "\n");
    }

    public static void playList(ArrayList<PlayableNote> notes) throws IOException {
        Collections.sort(notes);

        new Thread(() -> {
            int i = 0, siz = notes.size();
            Ticks current;
            for(i = 0; i < siz - 1; i++) {
                try {
                    Thread.sleep(0);
                    current = notes.get(i).timeOn;
                    playOne(notes.get(i));
                } catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
