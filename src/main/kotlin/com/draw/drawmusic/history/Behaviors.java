package com.draw.drawmusic.history;

import com.draw.drawmusic.tools.KeyBoardTool;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;

import java.util.ArrayList;

public abstract class Behaviors {
    protected static final ArrayList<Behaviors> historyList = new ArrayList<>();
    protected static int currentSession = -1;

    protected abstract void undoMethod();

    protected abstract void redoMethod();

    protected Behaviors() {
        addNewHistory();
        resetAfterCurrentSession();
    }

    private void addNewHistory() {
        currentSession++;
        historyList.add(currentSession, this);
    }

    private static void resetAfterCurrentSession() {
        historyList.subList(currentSession + 1, historyList.size()).clear();
    }

    public static void undo() {
        if(currentSession < 0) return;
        historyList.get(currentSession).undoMethod();
        currentSession--;
    }

    public static void redo() {
        if(currentSession + 1 >= historyList.size()) return;
        currentSession++;
        historyList.get(currentSession).redoMethod();
    }

    public static void matchKeyBoardShortCuts() {
        KeyBoardTool.setKeyEventHandler(Behaviors::undo, KeyCode.Z, KeyCombination.CONTROL_DOWN);
        KeyBoardTool.setKeyEventHandler(Behaviors::redo, KeyCode.Y, KeyCombination.CONTROL_DOWN);
    }
}
