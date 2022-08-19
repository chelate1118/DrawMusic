package com.draw.drawmusic.controllers

import com.draw.drawmusic.properties.Palette
import com.draw.drawmusic.tools.InteractBin
import com.draw.drawmusic.track.TrackBar
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
    }

    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        TrackBar.init(trackBarContent, trackBarScrollPane, trackBarResize)
        Palette.init("light_mode.json")
        InteractBin.connect()
    }
}