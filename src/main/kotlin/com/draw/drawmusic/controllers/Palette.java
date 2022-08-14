package com.draw.drawmusic.controllers;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class Palette {
    private static String theme;

    public static void init(String _theme) throws IOException {
        theme = _theme;
        parseJSON();
    }

    private static void parseJSON() throws IOException {
        String fileContent = Files.readString(Path.of("src/main/resources/com/draw/drawmusic/colors/dark_mode.json"));
        JSONObject jsonObject = new JSONObject(fileContent);
        String mantis = jsonObject.getString("Mantis");
        System.out.println(mantis);
    }
}
