package com.draw.drawmusic.tools;

public class SplineJoint extends Coordinate {
    public double dy;

    public SplineJoint(Coordinate p, float dy) {
        super(p.x, p.y);
        this.dy = dy;
    }

    public void setDy(Coordinate pre, Coordinate nex) {
        dy = Calculator.harmonicMean((x - pre.x) / (y - pre.y), (x - nex.x) / (y - nex.y));
    }
}
