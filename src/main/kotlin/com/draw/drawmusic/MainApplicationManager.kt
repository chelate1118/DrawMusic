package com.draw.drawmusic

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage

enum class WindowSize {
    NOT_MAXIMIZED, IS_MAXIMIZED
}

class MainApplicationManager : Application(){
    companion object {
        var windowWidth  : Double = 960.0
        var windowHeight : Double = 600.0
        lateinit var scene : Scene
        private var stage : Stage = Stage()
        val minWidth : Double = 960.0
        val minHeight : Double = 600.0

        private fun setScene(fxmlFilePath : String) {
            val fxmlLoader = FXMLLoader(MainApplicationManager::class.java.getResource(fxmlFilePath))
            scene = Scene(fxmlLoader.load(), windowWidth, windowHeight)
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
            stage.minWidth = minWidth
            stage.minHeight = minHeight
        }

        private fun setListenWindowSize() {
            stage.widthProperty().addListener{ _, _, newVal -> run{
                windowWidth = newVal as Double
            }}

            stage.heightProperty().addListener{ _, _, newVal -> run{
                windowHeight = newVal as Double
            }}
        }

        fun changeStage(fxmlFilePath: String, cssFilePath: String, title: String,
                        iconPath: String, isMaximized: WindowSize) {
            setScene(fxmlFilePath)
            setStyle(cssFilePath)
            setStage(title, iconPath, isMaximized)
            setListenWindowSize()

            stage.show()
        }

    }

    /*public fun addStage(fxmlFilePath: String, cssFilePath: String, title: String, iconPath: String, isMaximized: Boolean) {
        setScene(fxmlFilePath)
        setStyle(cssFilePath)
        setStage(title, iconPath, isMaximized)

        stage.show()
    }*/

    override fun start(primaryStage: Stage) {
        changeStage("start-view.fxml", "light_mode.css",
            "Draw Music", "icon.png", WindowSize.NOT_MAXIMIZED)

        stage.show()
    }

}

fun main() {
    Application.launch(MainApplicationManager::class.java)
}
