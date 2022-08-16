package com.draw.drawmusic.tools;

import java.io.IOException;

public class InteractBin {
    private static final String BIN_PATH = "bin/target/debug/bin";
    private static Runtime runtime;
    private static Process process;

    public static void connect() throws IOException {
        runtime = Runtime.getRuntime();
        process = runtime.exec(BIN_PATH);
    }
}
