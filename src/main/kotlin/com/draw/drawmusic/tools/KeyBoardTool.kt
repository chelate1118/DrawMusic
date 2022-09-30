package com.draw.drawmusic.tools

import com.draw.drawmusic.MainApplicationManager
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.input.KeyEvent

object KeyBoardTool {
    /**
     * example by ctrl+c - copy
     * @param keyCode KeyCode.C
     * @param modifier KeyCombination.ctrl
     * @param function Clipboard::add()
     */
    @JvmStatic
    fun setKeyEventHandler(function: Runnable, keyCode: KeyCode?, vararg modifier: KeyCombination.Modifier?) {
        MainApplicationManager.scene.addEventFilter(
            KeyEvent.KEY_PRESSED
        ) { keyEvent: KeyEvent? ->
            val keyCombination: KeyCombination = KeyCodeCombination(keyCode, *modifier)
            if (keyCombination.match(keyEvent)) {
                function.run()
            }
        }
    }
}