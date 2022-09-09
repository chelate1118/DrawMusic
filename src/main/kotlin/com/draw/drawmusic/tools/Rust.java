package com.draw.drawmusic.tools;

import java.io.File;

public class Rust {

    static {
//        System.load(new File("src/main/kotlin/com/draw/drawmusic/liblinux.so").getAbsolutePath());
        System.load(new File("audio/linux/target/release/liblinux.so").getAbsolutePath());
    }

    public static native int x(int a, int b);
}
