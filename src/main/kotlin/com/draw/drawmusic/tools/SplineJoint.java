package com.draw.drawmusic.tools;

import org.jetbrains.annotations.NotNull;

public class SplineJoint implements Comparable<SplineJoint> {
    public double x, y, dy;

    public SplineJoint(double x, double y, double dy) {
        this.x = x;
        this.y = y;
        this.dy = dy;
    }

    public void calculateDy(SplineJoint pre, SplineJoint nex) {
        dy = Calculator.harmonicMean((x - pre.x) / (y - pre.y), (x - nex.x) / (y - nex.y));
    }

    @Override
    public int compareTo(@NotNull SplineJoint o) {
        return Double.compare(x, o.x);
    }
}
