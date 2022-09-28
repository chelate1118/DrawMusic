package com.draw.drawmusic.track_properties

class Instrument private constructor(private val index: Int) {

    val name: String
        get() = instrumentName[index]

    companion object {
        private var GRANDPIANO = Instrument(0)
        private var VIOLIN = Instrument(1)
        private var FLUTE = Instrument(2)
        var DEFAULT = GRANDPIANO
        val instrumentList = ArrayList(
            listOf(GRANDPIANO, VIOLIN, FLUTE)
        )
        private val instrumentName = ArrayList(
            listOf("Grand Piano", "Violin", "Flute")
        )
    }
}