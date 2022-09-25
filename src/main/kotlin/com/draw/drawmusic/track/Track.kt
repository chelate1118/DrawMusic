package com.draw.drawmusic.track

import com.draw.drawmusic.editor.Editor
import com.draw.drawmusic.tools.Order
import com.draw.drawmusic.track_properties.Palette

abstract class Track(
    val trackElement: TrackController,
    var trackSelect: TrackSelect,
    val palette: Palette,
    val orderInTrackBar: Order
): Comparable<Track> {
    val id: Int = NEXT_ID++
    lateinit var editor: Editor

    companion object {
        var NEXT_ID = 1
    }

    override fun compareTo(other: Track): Int {
        return Order.compare(orderInTrackBar, other.orderInTrackBar)
    }
}