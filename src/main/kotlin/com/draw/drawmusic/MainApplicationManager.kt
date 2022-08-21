package com.draw.drawmusic

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage

enum class WindowSize {
    NOT_MAXIMIZED, IS_MAXIMIZED
}

class MainApplicationManager : Application() {
    companion object {
        private const val DEFAULT_WIDTH  : Double = 1200.0
        private const val DEFAULT_HEIGHT : Double = 800.0
        private const val MIN_WIDTH : Double = 960.0
        private const val MIN_HEIGHT : Double = 600.0

        lateinit var      scene : Scene
        var               stage : Stage = Stage()
        fun getResourceAsString(path : String): String? {
            return MainApplicationManager::class.java.getResource(path)?.toString()
        }

        private fun setScene(fxmlFilePath : String) {
            val fxmlLoader = FXMLLoader(MainApplicationManager::class.java.getResource(fxmlFilePath))
            scene = Scene(fxmlLoader.load(), DEFAULT_WIDTH, DEFAULT_HEIGHT)
        }

        private fun setStyle(cssFilePath : String) {
            scene.stylesheets.add(MainApplicationManager::class.java.getResource("css/".plus(cssFilePath))?.toString())
        }
        private fun setStage(title : String, iconPath : String,
                             isMaximized : WindowSize) {
            stage.title = title
            stage.scene = scene
            stage.icons.add(Image(MainApplicationManager::class.java.getResource("images/".plus(iconPath))?.toString()))
            stage.isMaximized = isMaximized == WindowSize.IS_MAXIMIZED
            stage.minWidth = MIN_WIDTH
            stage.minHeight = MIN_HEIGHT
        }

        fun changeStage(fxmlFilePath: String, cssFilePath: String, title: String,
                        iconPath: String, isMaximized: WindowSize) {
            setScene(fxmlFilePath)
            setStyle(cssFilePath)
            setStage(title, iconPath, isMaximized)

            stage.show()
        }

    }

    override fun start(primaryStage: Stage) {
        changeStage("start-view.fxml", "dark_mode.css",
            "Draw Music", "icon.png", WindowSize.NOT_MAXIMIZED)

        stage.show()
    }

}

fun main() {
    Application.launch(MainApplicationManager::class.java)
}
