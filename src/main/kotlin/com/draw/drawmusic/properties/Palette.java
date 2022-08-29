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

    /**
     * CalculatorException while calculate sigmoid
     */
    public Color darkColor(double change, double alpha) throws CalculatorException {
        return Color.hsb(color.getHue(), color.getSaturation(),
                Calculator.changeWithSigmoid(color.getBrightness(), -change), alpha);
    }

    /**
     * CalculatorException while calculate sigmoid
     */
    public Color darkColor(double change) throws CalculatorException {
        return Color.hsb(color.getHue(), color.getSaturation(),
                Calculator.changeWithSigmoid(color.getBrightness(), -change));
    }

    /**
     * CalculatorException while calculate sigmoid
     */
    public Color brightColor(double change, double alpha) throws CalculatorException {
        return darkColor(-change, alpha);
    }

    /**
     * CalculatorException while calculate sigmoid
     */
    public Color brightColor(double change) throws CalculatorException {
        return darkColor(-change);
    }
}
