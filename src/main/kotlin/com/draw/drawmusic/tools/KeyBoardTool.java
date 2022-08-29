package com.draw.drawmusic.tools;

import com.draw.drawmusic.MainApplicationManager;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class KeyBoardTool {
    /**
     * example by ctrl+c - copy
     * @param keyCode KeyCode.C
     * @param modifier KeyCombination.ctrl
     * @param function Clipboard::add()
     */
    public static void setKeyEventHandler(Runnable function, KeyCode keyCode, KeyCombination.Modifier... modifier) {
        MainApplicationManager.Companion.getScene().addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            final KeyCombination keyCombination = new KeyCodeCombination(keyCode, modifier);
            if (keyCombination.match(keyEvent)) {
                function.run();
            }
        });
    }
}
