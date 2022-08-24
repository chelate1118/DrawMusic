package com.draw.drawmusic.times;

import org.jetbrains.annotations.NotNull;

public class Ticks implements Comparable<Ticks> {
    public Bars bar;
    public int tick;

    public double to_double() {
        return bar.startTime + (double)tick / bar.denominator;
    }

    @Override
    public int compareTo(@NotNull Ticks o) {
        return Double.compare(to_double(), o.to_double());
    }
}
