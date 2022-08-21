package com.draw.drawmusic.tools;

import org.jetbrains.annotations.NotNull;

public abstract class Coordinate implements Comparable<Coordinate> {
    public double x, y;

    public Coordinate(double _x, double _y) {
        x = _x;
        y = _y;
    }

    @Override
    public int compareTo(@NotNull Coordinate o) {
        return Double.compare(x, o.x);
    }
}
