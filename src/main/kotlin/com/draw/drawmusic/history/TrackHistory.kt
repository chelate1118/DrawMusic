package com.draw.drawmusic.history

import com.draw.drawmusic.track.Track
import com.draw.drawmusic.track.TrackBar
import java.util.function.Consumer

class TrackHistory private constructor(
    private val track: Track,
    private val undoMethod: Consumer<Track>,
    private val redoMethod: Consumer<Track>
) :
    Behaviors() {
    override fun undoMethod() {
        undoMethod.accept(track)
    }

    override fun redoMethod() {
        redoMethod.accept(track)
    }

    companion object {
        private val add = TrackBar::addElement
        private val delete = TrackBar::deleteElement
        private val moveUp = TrackBar::moveUp
        private val moveDown = TrackBar::moveDown

        fun saveAddHistory(`object`: Track) {
            TrackHistory(`object`, delete, add)
        }

        fun saveDeleteHistory(`object`: Track) {
            TrackHistory(`object`, add, delete)
        }

        fun saveMoveUpHistory(`object`: Track) {
            TrackHistory(`object`, moveDown, moveUp)
        }

        fun saveMoveDownHistory(`object`: Track) {
            TrackHistory(`object`, moveUp, moveDown)
        }
    }
}
