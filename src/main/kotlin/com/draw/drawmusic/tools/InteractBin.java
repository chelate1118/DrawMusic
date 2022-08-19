package com.draw.drawmusic.tools;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class InteractBin {
    private static final String BIN_PATH = "bin/target/debug/bin";
    private static BufferedWriter writer;

    public static void connect() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(BIN_PATH);
        writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
    }

    public static void sendCommand(String command) throws IOException {
        command += '\n';
        writer.write(command);
        writer.flush();
    }
}
