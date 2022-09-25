package com.draw.drawmusic.rust;

import java.io.File;

public class Rust {
    private static final String PATH = "/linux/target/release/liblinux.so";

    static {
        System.load(new File("audio" + PATH).getAbsolutePath());
    }

    public static native void init();
}
