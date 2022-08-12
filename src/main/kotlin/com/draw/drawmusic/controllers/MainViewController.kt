package com.draw.drawmusic.controllers

import javafx.fxml.Initializable
import javafx.scene.control.ScrollPane
import javafx.scene.layout.VBox
import java.net.URL
import java.util.*

class MainViewController : Initializable {
    lateinit var trackBarContent: VBox
    lateinit var trackBarScrollPane : ScrollPane
    lateinit var trackBarResize: VBox

    fun plusButtonClicked() {
        TrackBar.addElement()
        TrackBar.showElements()
    }

    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        TrackBar.init(trackBarContent, trackBarScrollPane, trackBarResize)
    }
}