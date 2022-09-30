package com.draw.drawmusic.track

import com.draw.drawmusic.history.TrackHistory
import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.control.TextField
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.shape.Circle

abstract class TrackController {
    companion object {
        protected const val BRIGHTER1 = 3.0

        protected const val DARKER1 = 0.7
        protected const val DARKER2 = 1.0

        protected const val ALPHA1 = 0.2
        protected const val ALPHA2 = 0.4
        @JvmStatic protected val ALPHA3 = 0.5

        protected const val RADIUS = 10.0

        protected const val BORDER = 3.0

        protected const val UNDERLINE_WIDTH = 1.5
    }

    @FXML lateinit var colorCircle: Circle
    @FXML lateinit var inputTrackName: TextField
    @FXML lateinit var gridPane: GridPane

    protected abstract val parent: Track

    protected abstract fun makeShape()
    abstract fun updateShape()

    @FXML
    fun moveUp() {
        TrackHistory.saveMoveUpHistory(parent)
        TrackBar.moveUp(parent)
    }

    @FXML
    fun moveDown() {
        TrackHistory.saveMoveDownHistory(parent)
        TrackBar.moveDown(parent)
    }

    @FXML
    fun trashButtonClicked() {
        TrackHistory.saveDeleteHistory(parent)
        TrackBar.deleteElement(parent)
    }

    protected fun makeColorCircle() {
        colorCircle.stroke = parent.palette.color
        colorCircle.fill = parent.palette.brightColor(BRIGHTER1)
    }

    protected fun makeInputTrackName() {
        inputTrackName.text = "Track ${parent.id}"

        inputTrackName.background = Background(
            BackgroundFill(
                Color.hsb(0.0, 0.0, 0.0, ALPHA1),
                CornerRadii.EMPTY,
                Insets.EMPTY
            )
        )

        inputTrackName.border = Border(
            BorderStroke(
                parent.palette.darkColor(DARKER2),
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths(0.0, 0.0, UNDERLINE_WIDTH, 0.0)
            )
        )
    }

    protected fun makeGridPane() {
        gridPane.background = Background(
            BackgroundFill(
                parent.palette.brightColor(BRIGHTER1, ALPHA2),
                CornerRadii(RADIUS + 3),
                Insets.EMPTY
            )
        )

        gridPane.border = Border(
            BorderStroke(
                parent.palette.darkColor(DARKER1),
                BorderStrokeStyle.SOLID,
                CornerRadii(RADIUS),
                BorderWidths(BORDER)
            )
        )

        VBox.setMargin(gridPane, Insets(5.0))
    }

    protected fun updateGridPane() {
        val borderColor: Color = when(parent.trackSelect) {
            TrackSelect.UnSelected -> parent.palette.darkColor(DARKER1)
            else -> parent.palette.brightColor(BRIGHTER1)
        }

        gridPane.border = Border(
            BorderStroke(
                borderColor,
                BorderStrokeStyle.SOLID,
                CornerRadii(RADIUS),
                BorderWidths(BORDER)
            )
        )
    }
}
