package com.draw.drawmusic.tools;

import org.jetbrains.annotations.NotNull;

public class Ticks implements Comparable<Ticks> {
    public int bar;
    public Rational tick;

    @Override
    public int compareTo(@NotNull Ticks o) {
        return 0;
    }
}
