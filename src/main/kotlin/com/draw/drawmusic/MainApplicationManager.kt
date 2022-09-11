package com.draw.drawmusic

import com.draw.drawmusic.history.Behaviors
import com.draw.drawmusic.track.TrackBar
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage
import java.net.URL

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
        fun changeStage(fxmlFilePath: String, cssFilePath: String, title: String,
                        iconPath: String, isMaximized: WindowSize) {
            setScene(fxmlFilePath)
            setKeyboardShortcuts()
            setStyle(cssFilePath)
            setStage(title, iconPath, isMaximized)
        }

        private fun setScene(fxmlFilePath : String) {
            val fxmlLoader = FXMLLoader(getResource("fxmls/".plus(fxmlFilePath)))
            scene = Scene(fxmlLoader.load(), DEFAULT_WIDTH, DEFAULT_HEIGHT)
        }

        private fun setKeyboardShortcuts() {
            TrackBar.matchKeyboardShortcuts()
            Behaviors.matchKeyBoardShortCuts()
        }

        private fun setStyle(cssFilePath : String) {
            scene.stylesheets.add(getResource("css/".plus(cssFilePath))?.toString())
        }
        private fun setStage(title : String, iconPath : String,
                             isMaximized : WindowSize) {
            stage.title = title
            stage.scene = scene
            stage.icons.add(Image(getResourceAsString("images/".plus(iconPath))))
            stage.isMaximized = (isMaximized == WindowSize.IS_MAXIMIZED)
            stage.minWidth = MIN_WIDTH
            stage.minHeight = MIN_HEIGHT
            stage.show()
        }

        private fun getResourceAsString(path : String): String? {
            return MainApplicationManager::class.java.getResource(path)?.toString()
        }

        private fun getResource(path : String) : URL? {
            return MainApplicationManager::class.java.getResource(path)
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
