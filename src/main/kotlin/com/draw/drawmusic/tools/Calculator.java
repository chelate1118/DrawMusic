package com.draw.drawmusic.tools;

public class Calculator {
    private static final double SIGMOID_MAX_APPROXIMATION = 5.0;
    private static final double SIGMOID_MIN_APPROXIMATION = -5.0;

    private static double sigmoid(double x) {
        if(x > SIGMOID_MAX_APPROXIMATION) return 1;
        if(x < SIGMOID_MIN_APPROXIMATION) return 0;
        return (1/(1+Math.exp(-x)));
    }

    private static double sigmoidInverse(double x) throws CalculatorException {
        if(x == 1) return SIGMOID_MAX_APPROXIMATION;
        if(x == 0) return SIGMOID_MIN_APPROXIMATION;
        if(x > 1 || x < 0) {
            throw new CalculatorException("Sigmoid value can't be greater than 1 or less than 0");
        } else {
            return Math.max(Math.min(Math.log(x/(1-x)), SIGMOID_MAX_APPROXIMATION), SIGMOID_MIN_APPROXIMATION);
        }
    }

    private static double changeValueLinearly(double val, double min1, double max1, double min2, double max2) throws CalculatorException {
        if(val > max1 || val < min1) throw new CalculatorException("Value isn't in range");
        if(min1 >= max1 || min2 >= max2) throw new CalculatorException("Minimum value is greater than Maximum value or the range is to small");
        double val1 = (val - min1) / (max1 - min1);
        return val1 * (max2 - min2) + min2;
    }

    public static double changeWithSigmoidInRange(double min, double max, double val, double change) throws CalculatorException {
        if(min >= max) throw new CalculatorException("Minimum value is greater than maximum value or the range is too small");
        return changeValueLinearly(sigmoid(sigmoidInverse(
                changeValueLinearly(val, min, max, 0, 1)) + change),
                0, 1, min, max);
    }

    public static double changeWithSigmoid(double val, double change) {
        double ans = 0;
        try {
            ans = changeWithSigmoidInRange(0, 1, val, change);
        } catch (CalculatorException ignored) {

        }

        return ans;
    }

    public static double harmonicMean(double x, double y) {
        if(x == 0 || y == 0) return 0;
        return 2 / (1 / x + 1 / y);
    }
}
