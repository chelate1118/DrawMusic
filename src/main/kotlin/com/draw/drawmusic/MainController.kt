package com.draw.drawmusic

import javafx.fxml.Initializable
import javafx.scene.control.ScrollPane
import javafx.scene.layout.VBox
import java.net.URL
import java.util.*

class MainController : Initializable {
    lateinit var trackBar: VBox
    lateinit var trackBarScrollPane : ScrollPane
    lateinit var trackBarResize: VBox

    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        TrackBar.init(trackBarScrollPane, trackBar, trackBarResize)
    }
}