package com.draw.drawmusic.properties;

import com.draw.drawmusic.tools.Calculator;
import com.draw.drawmusic.tools.CalculatorException;
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
        System.out.println("[Palette : init()] Palette initialized : Read " + theme);
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

    public Color getDarkColor(double change, double alpha) throws CalculatorException {
        return Color.hsb(color.getHue(), color.getSaturation(),
                Calculator.changeWithSigmoid(color.getBrightness(), -change), alpha);
    }

    public Color getBrightColor(double change, double alpha) throws CalculatorException {
        return getDarkColor(-change, alpha);
    }
}
