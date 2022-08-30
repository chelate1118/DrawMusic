package com.draw.drawmusic.track_properties;

import com.draw.drawmusic.tools.Calculator;
import com.draw.drawmusic.tools.ResourcePaths;
import javafx.scene.paint.Color;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

public record Palette(Color color) {
    private static String theme;
    private static JSONObject jsonObject;
    private static Iterator<String> generateNext;

    public static void init(String _theme) throws IOException {
        theme = _theme;
        parseJSON();
    }

    private static void parseJSON() throws IOException {
        String fileContent = Files.readString(Path.of(ResourcePaths.getPathFromContentRoot("colors", theme)));
        jsonObject = new JSONObject(fileContent);
        generateNext = jsonObject.keys();
    }

    public static Palette next() {
        if (!generateNext.hasNext())
            generateNext = jsonObject.keys();
        return new Palette(Color.web(jsonObject.getString(generateNext.next())));
    }

    public Color darkColor(double change, double alpha) {
        return Color.hsb(color.getHue(), color.getSaturation(),
                Calculator.changeWithSigmoid(color.getBrightness(), -change), alpha);
    }

    public Color darkColor(double change) {
        return Color.hsb(color.getHue(), color.getSaturation(),
                Calculator.changeWithSigmoid(color.getBrightness(), -change));
    }

    public Color brightColor(double change, double alpha) {
        return darkColor(-change, alpha);
    }

    public Color brightColor(double change) {
        return darkColor(-change);
    }
}
