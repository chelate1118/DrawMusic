package com.draw.drawmusic.controllers

import com.draw.drawmusic.editor.Editor
import com.draw.drawmusic.editor.KeyboardEditor
import com.draw.drawmusic.editor.SplineGraphEditor
import com.draw.drawmusic.rust.Rust
import com.draw.drawmusic.track.TrackBar
import com.draw.drawmusic.track_properties.Palette
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ScrollPane
import javafx.scene.control.ToolBar
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import java.net.URL
import java.util.*

class MainViewController : Initializable {
    @FXML
    lateinit var trackBarContent: VBox
    lateinit var trackBarScrollPane : ScrollPane
    lateinit var toolBar : ToolBar
    lateinit var editorTopSide: VBox
    lateinit var editorStackPane : StackPane
    lateinit var editorScrollPane: ScrollPane
    lateinit var keyboardScrollPane: ScrollPane

    @FXML
    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        Palette.init("light_mode.json")
        TrackBar.init(trackBarContent, trackBarScrollPane, toolBar)
        SplineGraphEditor.init(editorTopSide)
        KeyboardEditor.init(keyboardScrollPane, editorScrollPane)
        Editor.init(editorStackPane)
        Rust.init()
    }
}