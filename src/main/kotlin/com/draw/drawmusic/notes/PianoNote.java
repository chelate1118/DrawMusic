package com.draw.drawmusic.notes;

import javafx.scene.effect.DropShadow;
import javafx.scene.shape.Rectangle;

public class PianoNote extends PlayNote {
    public ViolinNote toViolinNote() {
        return null;
    }

    @Override
    public Rectangle toShape(boolean isSelected) {
        Rectangle ret = new Rectangle(-10, -10, 100, 100);
        ret.setFill(palette.color());

        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(palette.color());
        borderGlow.setHeight(70);
        borderGlow.setWidth(70);

        ret.setEffect(borderGlow);

        return ret;
    }
}
