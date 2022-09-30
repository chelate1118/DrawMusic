package com.draw.drawmusic.track

import com.draw.drawmusic.controllers.FXMLController.Companion.fxmlLoad
import com.draw.drawmusic.history.TrackHistory
import com.draw.drawmusic.tools.ArrayTool
import com.draw.drawmusic.tools.KeyBoardTool.setKeyEventHandler
import com.draw.drawmusic.tools.Order
import javafx.animation.FadeTransition
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import javafx.scene.control.ToolBar
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCombination
import javafx.scene.layout.VBox
import javafx.util.Duration

// TODO: 트랙 삭제 후 ctrl-z 하면 에러

// TODO: 트랙 삭제 후 ctrl-z 하면 에러
object TrackBar {
    private const val TRACK_MAX_NUMBER = 50
    private var toolBar: ToolBar? = null
    private var contentBar: VBox? = null
    private var scrollPane: ScrollPane? = null
    private var displayTrackNumbers: Label? = null
    private var addButton: Button? = null
    private val trackElements = ArrayList<Track>()
    private val selectedList: ArrayList<Track>
        get() {
            val selected = ArrayList<Track>()
            for (element in trackElements) {
                if (element.trackSelect !== TrackSelect.UnSelected) {
                    selected.add(element)
                }
            }
            return selected
        }

    fun init(_trackBarContent: VBox?, _trackBarScrollPane: ScrollPane?, _toolBar: ToolBar?) {
        contentBar = _trackBarContent
        scrollPane = _trackBarScrollPane
        toolBar = _toolBar
        fitScrollPaneSize()
        setToolBar()
    }

    private fun fitScrollPaneSize() {
        scrollPane!!.isFitToWidth = true
        scrollPane!!.isFitToHeight = true
    }

    private fun setToolBar() {
        matchToolBarItems()
        setToolBarEvent()
    }

    fun matchKeyboardShortcuts() {
        setKeyEventHandler(
            TrackBar::deleteSelectedElements,
            KeyCode.DELETE,
            KeyCombination.SHIFT_DOWN
        )

        setKeyEventHandler(
            TrackBar::addButtonClicked,
            KeyCode.N,
            KeyCombination.CONTROL_DOWN
        )
    }

    private fun addButtonClicked() {
        loadTrackElementFXML()
        TrackHistory.saveAddHistory(ArrayTool.last(trackElements))
    }

    fun addElement(newElement: Track) {
        trackElements.add(newElement)
        selectElements(newElement, ctrlClicked = false, shiftClicked = false)
        displayAndUpdateElements()
        fadeInElement(newElement)
        scrollDown()
        setDisplayTrackNumbers()
        for (i in trackElements) {
            println(i.toString() + " : " + i.orderInTrackBar)
        }
        println("====================")
    }

    private fun displayAndUpdateElements() {
        displayElements()
        updateElements()
    }

    private fun selectAndUpdateElements(element: Track) {
        selectElements(element, ctrlClicked = false, shiftClicked = false)
        updateElements()
    }

    fun selectAndUpdateElements(element: Track, ctrlClicked: Boolean, shiftClicked: Boolean) {
        selectElements(element, ctrlClicked, shiftClicked)
        updateElements()
    }

    private fun deleteSelectedElements() {
        for (element in selectedList) {
            deleteElement(element)
        }
    }

    fun deleteElement(track: Track) {
        selectAndUpdateElements(ArrayTool.closeElement(trackElements, track))
        trackElements.remove(track)
        setDisplayTrackNumbers()
        displayElements()
    }

    fun moveUp(a: Track) {
        switchTwoTracks(a, ArrayTool.previous(trackElements, a))
    }

    fun moveDown(a: Track) {
        switchTwoTracks(a, ArrayTool.next(trackElements, a))
    }

    fun makeLastOrder(): Order {
        return if (trackElements.isEmpty()) Order.DEFAULT else Order.nextOrderThan(ArrayTool.last(trackElements).orderInTrackBar)
    }

    private fun switchTwoTracks(a: Track, b: Track) {
        Order.swap(a.orderInTrackBar, b.orderInTrackBar)
        syncOrderInDisplay()
        displayElements()
    }

    private fun syncOrderInDisplay() {
        trackElements.sort()
    }

    private fun matchToolBarItems() {
        addButton = ArrayTool.first(toolBar!!.items) as Button
        displayTrackNumbers = ArrayTool.last(toolBar!!.items) as Label
        displayTrackNumbers!!.text = "0/$TRACK_MAX_NUMBER"
    }

    private fun setToolBarEvent() {
        addButton!!.onAction = EventHandler { _: ActionEvent? -> addButtonClicked() }
    }

    private fun loadTrackElementFXML() {
        fxmlLoad("midi-track.fxml")
    }

    private fun setDisplayTrackNumbers() {
        displayTrackNumbers!!.text = trackElements.size.toString() + "/" + TRACK_MAX_NUMBER
    }

    private fun displayElements() {
        contentBar!!.children.clear()
        for (element in trackElements) {
            contentBar!!.children.add(element.trackElement.gridPane)
        }
    }

    private fun updateElements() {
        for (element in trackElements) {
            element.trackElement.updateShape()
        }
    }

    private fun fadeInElement(node: Track) {
        val ft = FadeTransition(Duration.millis(500.0), node.trackElement.gridPane)
        ft.fromValue = 0.0
        ft.toValue = 1.0
        ft.play()
    }

    private fun scrollDown() {
        scrollPane!!.vvalue = scrollPane!!.vmax
    }

    private fun selectElements(element: Track, ctrlClicked: Boolean, shiftClicked: Boolean) {
        if (shiftClicked) {
            val index = trackElements.indexOf(element)
            val siz = trackElements.size
            var start = -1
            for (i in 0 until siz) {
                if (trackElements[i].trackSelect === TrackSelect.CurrentSelected) {
                    start = i
                }
            }
            if (start == -1) {
                element.trackSelect = TrackSelect.CurrentSelected
            } else {
                for (_element in trackElements) _element.trackSelect = TrackSelect.UnSelected
                for (i in Integer.min(index, start)..Integer.max(index, start)) {
                    if (i == start) trackElements[i].trackSelect =
                        TrackSelect.CurrentSelected else trackElements[i].trackSelect = TrackSelect.MultiSelected
                }
            }
        } else if (ctrlClicked) {
            if (element.trackSelect !== TrackSelect.UnSelected) {
                element.trackSelect = TrackSelect.UnSelected
            } else {
                for (_element in trackElements) {
                    if (_element.trackSelect === TrackSelect.CurrentSelected) _element.trackSelect =
                        TrackSelect.MultiSelected
                }
                element.trackSelect = TrackSelect.CurrentSelected
            }
        } else {
            for (_element in trackElements) {
                _element.trackSelect = TrackSelect.UnSelected
            }
            element.trackSelect = TrackSelect.CurrentSelected
        }
    }
}
