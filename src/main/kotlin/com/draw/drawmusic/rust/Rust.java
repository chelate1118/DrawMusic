package com.draw.drawmusic.rust;

import java.io.File;
import java.util.Arrays;

public class Rust {
    private static final String PATH = "/linux/target/release/liblinux.so";

    static {
        System.load(new File("audio" + PATH).getAbsolutePath());
    }

    public static int x(int[] arr) {
        arr = Arrays.copyOf(arr, 100);
        return xx(arr);
    }

    private static native int xx(int[] arr);
    public static native void init();
}
