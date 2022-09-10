package com.draw.drawmusic.tools;

public class ResourcePaths {
    private static final String RESOURCE_PATH = "src/main/generated/com/draw/drawmusic/";

    public static String getPathFromContentRoot(String folder, String file) {
        return RESOURCE_PATH + folder + "/" + file;
    }
}
