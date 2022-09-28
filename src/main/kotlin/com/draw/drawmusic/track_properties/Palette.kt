package com.draw.drawmusic.track_properties

import com.draw.drawmusic.tools.Calculator
import com.draw.drawmusic.tools.ResourcePaths
import javafx.scene.paint.Color
import org.json.JSONObject
import java.nio.file.Files
import java.nio.file.Path

data class Palette(val color: Color) {
    companion object {
        private lateinit var theme: String
        private lateinit var jsonObject: JSONObject
        private lateinit var generateNext: Iterator<String>

        fun init(theme: String) {
            this.theme = theme
            parseJSON()
        }

        private fun parseJSON() {
            val fileContent = Files.readString(Path.of(ResourcePaths.getPathFromContentRoot("colors", theme)))
            jsonObject = JSONObject(fileContent)
            generateNext = jsonObject.keys()
        }

        @JvmStatic
        fun next(): Palette {
            if (!generateNext.hasNext())
                generateNext = jsonObject.keys()
            return Palette(Color.web(jsonObject.getString(generateNext.next())))
        }
    }

    fun darkColor(change: Double, alpha: Double): Color {
        return Color.hsb(
            color.hue, color.saturation,
            Calculator.changeWithSigmoid(color.brightness, -change), alpha
        )
    }

    fun darkColor(change: Double): Color {
        return Color.hsb(
            color.hue, color.saturation,
            Calculator.changeWithSigmoid(color.brightness, -change)
        )
    }

    fun brightColor(change: Double, alpha: Double): Color {
        return darkColor(-change, alpha)
    }

    fun brightColor(change: Double): Color {
        return darkColor(-change)
    }
}