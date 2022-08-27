package com.draw.drawmusic.controllers

import com.draw.drawmusic.MainApplicationManager
import javafx.fxml.FXMLLoader
import javafx.scene.layout.Pane

class FXMLController {
    companion object {
        fun fxmlToPane(path: String) : Pane {
            val fxmlLoader = FXMLLoader(MainApplicationManager::class.java.getResource("fxmls/".plus(path)))
            return fxmlLoader.load()
        }
        fun fxmlLoad(path: String) {
            FXMLLoader(MainApplicationManager::class.java.getResource("fxmls/".plus(path))).load<Pane>()
        }
    }
}