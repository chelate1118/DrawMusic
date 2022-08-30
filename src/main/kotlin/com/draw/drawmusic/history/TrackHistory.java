package com.draw.drawmusic.history;

import com.draw.drawmusic.track.Track;
import com.draw.drawmusic.track.TrackBar;

import java.util.function.Consumer;

public class TrackHistory extends Behaviors{
    Track object;

    private static final Consumer<Track> add = TrackBar::addElement;
    private static final Consumer<Track> delete = TrackBar::deleteElement;
    private static final Consumer<Track> moveUp = TrackBar::moveUp;
    private static final Consumer<Track> moveDown = TrackBar::moveDown;

    private final Consumer<Track> undoMethod;
    private final Consumer<Track> redoMethod;

    protected TrackHistory(Track object, Consumer<Track> undoMethod, Consumer<Track> redoMethod) {
        super();
        this.object = object;
        this.undoMethod = undoMethod;
        this.redoMethod = redoMethod;
    }

    @Override
    protected void undoMethod() {
        undoMethod.accept(object);
    }

    @Override
    protected void redoMethod() {
        redoMethod.accept(object);
    }

    public static void saveAddHistory(Track object) {
        new TrackHistory(object, delete, add);
    }

    public static void saveDeleteHistory(Track object) {
        new TrackHistory(object, add, delete);
    }

    public static void saveMoveUpHistory(Track object) {
        new TrackHistory(object, moveDown, moveUp);
    }

    public static void saveMoveDownHistory(Track object) {
        new TrackHistory(object, moveUp, moveDown);
    }
}
