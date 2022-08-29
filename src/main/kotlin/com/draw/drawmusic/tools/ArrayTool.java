package com.draw.drawmusic.tools;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ArrayTool {
    public static <Type> Type last(@NotNull List<Type> array) {
        return array.get(array.size() - 1);
    }

    public static <Type> Type first(@NotNull List<Type> array) {
        return array.get(0);
    }

    public static <Type> Type previous(@NotNull List<Type> array, int index) {
        if(index > 0) index--;
        return array.get(index);
    }

    public static <Type> Type previous(@NotNull List<Type> array, Type element) {
        int index = array.indexOf(element);
        return previous(array, index);
    }

    public static <Type> Type next(@NotNull List<Type> array, int index) {
        if(index + 1 < array.size()) index++;
        return array.get(index);
    }

    public static <Type> Type next(@NotNull List<Type> array, Type element) {
        int index = array.indexOf(element);
        return next(array, index);
    }
}
