package com.draw.drawmusic.track

import com.draw.drawmusic.track_properties.Instrument
import com.draw.drawmusic.track_properties.Palette
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.geometry.Insets
import javafx.scene.control.MenuButton
import javafx.scene.control.MenuItem
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Color
import java.net.URL
import java.util.*

class MidiTrackController : TrackController(), Initializable {
    @FXML
    lateinit var chooseInstrument: MenuButton

    override val parent: MIDITrack = MIDITrack(this, TrackSelect.UnSelected, Palette.next(), TrackBar.makeLastOrder())

    @FXML
    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        makeShape()
        makeEventControl()
        TrackBar.addElement(parent)
    }

    override fun updateShape() {
        updateGridPane()
    }

    override fun makeShape() {
        this.makeColorCircle()
        this.makeInputTrackName()
        this.makeGridPane()
        makeChooseInstrument()
    }

    private fun makeChooseInstrument() {
        chooseInstrument.background = Background(
            BackgroundFill(
                Color.hsb(0.0, 0.0, 0.0, ALPHA3),
                CornerRadii(2.0), Insets.EMPTY
            )
        )
        chooseInstrument.text = parent.instrument.name
        for (instrument in Instrument.getInstrumentList()) {
            chooseInstrument.items.add(makeMenuItem(instrument))
        }
    }

    private fun makeMenuItem(_instrument: Instrument): MenuItem {
        val menuItem = MenuItem(_instrument.name)
        menuItem.onAction = EventHandler { _: ActionEvent? ->
            parent.instrument = _instrument
            chooseInstrument.text = _instrument.name
        }
        return menuItem
    }

    private fun makeEventControl() {
        gridPane.addEventFilter(MouseEvent.MOUSE_CLICKED) { mouseEvent: MouseEvent ->
            if (mouseEvent.button == MouseButton.PRIMARY) TrackBar.selectAndUpdateElements(
                parent,
                mouseEvent.isControlDown,
                mouseEvent.isShiftDown
            )
        }
    }
}