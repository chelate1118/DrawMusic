package com.draw.drawmusic.properties;

import com.draw.drawmusic.tools.Calculator;
import com.draw.drawmusic.tools.CalculatorException;
import javafx.scene.paint.Color;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

public class Palette {
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
        if(!generateNext.hasNext())
            generateNext = jsonObject.keys();
        return new Palette(Color.web(jsonObject.getString(generateNext.next())));
    }

    private Color color;

    public Palette(Color color) { this.color = color; }
    public void setColor(Color color) { this.color = color; }

    public Color getColor() { return color; }
    public Color getDarkColor(double change) throws CalculatorException {
        System.out.println(color.getBrightness());
        return Color.hsb(color.getHue(), color.getSaturation(),
                Calculator.changeWithSigmoid(color.getBrightness(), -change));
    }

    public Color getBrightColor(double change) throws CalculatorException {
        return getDarkColor(-change);
    }
}
