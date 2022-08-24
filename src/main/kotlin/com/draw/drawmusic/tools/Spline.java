package com.draw.drawmusic.tools;

import Jama.Matrix;

import java.util.ArrayList;
import java.util.Collections;

class Tertiary {
    public double start, end;
    public double[] coefficients;

    public Tertiary(SplineJoint s, SplineJoint e) {
        double[][] arr = {
                {Math.pow(s.x, 3), Math.pow(s.x, 2), s.x, 1},
                {Math.pow(e.x, 3), Math.pow(e.x, 2), e.x, 1},
                {3 * Math.pow(s.x, 2), 2 * s.x, 1, 0},
                {3 * Math.pow(e.x, 2), 2 * e.x, 1, 0},
        };
        Matrix mat = new Matrix(arr);
        Matrix con = new Matrix(new double[][]{
                {s.y}, {e.y}, {s.dy}, {e.dy}
        });
        Matrix ans = mat.inverse().times(con);
        coefficients = ans.getColumnPackedCopy();
        start = s.x;
        end = e.x;
    }

    public boolean inRange(double x) {
        return start <= x && x <= end;
    }
    public double getVal(double x) {
        double ans = 0;
        for(double coe : coefficients) {
            ans *= x;
            ans += coe;
        }
        return ans;
    }
}

public class Spline {
    private final ArrayList<Tertiary>    fun    = new ArrayList<>();
    private final ArrayList<SplineJoint> points = new ArrayList<>();

    public void addPoint(SplineJoint point) {
        points.add(point);
        Collections.sort(points);
    }

    public void deletePoint(double x) {
        points.removeIf(n -> n.x == x);
    }

    public double getVal(double x) {
        for(Tertiary f : fun) {
            if(f.inRange(x)) return f.getVal(x);
        }
        if(x < fun.get(0).start) return fun.get(0).getVal(fun.get(0).start);
        return fun.get(fun.size() - 1).getVal(fun.get(fun.size() - 1).end);
    }

    private void calculateSpline() {
        int siz = points.size();
        for(int i = 0; i < siz; i++) {
            points.set(i, new SplineJoint(points.get(i).x, points.get(i).y, 0));
            if(i > 0 && i < siz - 1) points.get(i).calculateDy(points.get(i - 1), points.get(i + 1));
        }
        fun.clear();
        fun.trimToSize();
        for(int i = 0; i < siz - 1; i++) {
            fun.add(new Tertiary(points.get(i), points.get(i + 1)));
        }
    }
}
