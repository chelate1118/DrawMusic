package com.draw.drawmusic.tools;

import Jama.Matrix;

public class Tertiary { // 삼차함수
    double start, end;
    double[] coefficients;

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

    public Tertiary(double[] coefficients) {
        this.coefficients = coefficients;
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

    public double definiteIntegral(double start, double end) {
        if(!inRange(start) || !inRange(end)) throw new RuntimeException();
        Tertiary tmp = new Tertiary(new double[]{coefficients[0]/4, coefficients[1]/3,
                coefficients[2]/2, coefficients[3]});
        return tmp.getVal(end) * end - tmp.getVal(start) * start;
    }
}
