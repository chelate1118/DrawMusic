package com.draw.drawmusic.controllers

import com.draw.drawmusic.notes.Editor
import com.draw.drawmusic.properties.Palette
import com.draw.drawmusic.tools.InteractBin
import com.draw.drawmusic.track.TrackBar
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ScrollPane
import javafx.scene.control.ToolBar
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import java.net.URL
import java.util.*

class MainViewController : Initializable {
    @FXML lateinit var trackBarContent: VBox
    @FXML lateinit var trackBarScrollPane : ScrollPane
    @FXML lateinit var stackPane : StackPane
    @FXML lateinit var toolBar : ToolBar

    @FXML
    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        Palette.init("light_mode.json")
        TrackBar.init(trackBarContent, trackBarScrollPane, toolBar)
        Editor.init(stackPane)
        InteractBin.connect()
    }
}