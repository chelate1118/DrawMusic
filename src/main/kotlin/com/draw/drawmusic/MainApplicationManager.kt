package com.draw.drawmusic

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage

class MainApplicationManager : Application(){
    companion object {
        var windowWidth  : Double = 800.0
        var windowHeight : Double = 500.0
    }

    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(MainApplicationManager::class.java.getResource("hello-view.fxml"))
        val scene = Scene(fxmlLoader.load(), windowWidth, windowHeight)
        scene.stylesheets.add(MainApplicationManager::class.java.getResource("css/dark_mode.css")?.toString())
        stage.title = "Draw Music"
        stage.scene = scene
//        stage.isMaximized = true
        val icon = Image(MainApplicationManager::class.java.getResource("images/icon.png")?.toString())
        stage.icons.add(icon)

        stage.heightProperty().addListener { _, _, newVal ->
            run {
                windowHeight = newVal as Double
                TrackBar.manageSize()
            }
        }

        stage.show()
    }
}

fun main() {
    Application.launch(MainApplicationManager::class.java)
}