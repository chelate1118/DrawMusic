package com.draw.drawmusic.notes;

import javafx.scene.effect.DropShadow;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class PianoNote extends PlayNote {
    public PianoNote(Editor _parent) {
        super(_parent);
    }

    public ViolinNote toViolinNote() {
        return null;
    }

    @Override
    public Rectangle toShape(NoteSelect isSelected) {
//        Scanner in = new Scanner(System.in);
//        int num = in.nextInt();
//        System.out.println(num + " " + pitchToY(num));
        Rectangle ret = new Rectangle(0, new Random().nextDouble(500), 100, NOTE_HEIGHT);
        System.out.println(ret.getY());
        ret.setFill(palette.color());

        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(palette.color());
        borderGlow.setHeight(70);
        borderGlow.setWidth(70);

        ret.setEffect(borderGlow);

        return ret;
    }
}
