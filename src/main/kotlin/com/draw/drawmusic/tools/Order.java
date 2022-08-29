package com.draw.drawmusic.tools;

public class Order {
    private double value;

    private Order(double value) {
        this.value = value;
    }

    private static final double BIGGER_AMOUNT = 5.0;

    public static final Order DEFAULT = new Order(0.0);

    public static Order nextOrderThan(Order lastOrder) {
        return new Order(lastOrder.value + BIGGER_AMOUNT);
    }

    public static Order betweenOrder(Order a, Order b) {
        return new Order((a.value + b.value) / 2);
    }

    public static Order previousOrderThan(Order firstOrder) {
        return new Order(firstOrder.value - BIGGER_AMOUNT);
    }

    public static void swap(Order a, Order b) {
        double tmp = a.value;
        a.value = b.value;
        b.value = tmp;
    }

    public static int compare(Order a, Order b) {
        return Double.compare(a.value, b.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
