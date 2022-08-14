package com.draw.drawmusic.properties;

import com.draw.drawmusic.MainApplicationManager;

public class ResourcePaths {
    private static final String RESOURCE_PATH = "src/main/resources/com/draw/drawmusic/";

    public static String getAbsolutePath(String folder, String file) {
        return MainApplicationManager.Companion.getResourceAsString(folder + "/" + file);
    }

    public static String getAbsolutePath(String file) {
        return MainApplicationManager.Companion.getResourceAsString(file);
    }

    public static String getPathFromContentRoot(String folder, String file) {
        return RESOURCE_PATH + folder + "/" + file;
    }

    public static String getPathFromContentRoot(String file) {
        return RESOURCE_PATH + file;
    }
}
