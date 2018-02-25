package Models;

import Models.Prototypes.Drawable;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominik on 2016-12-26.
 */
public class LayerCaretaker {

    private Layer layer;

    private List<Layer.Memento> savedStates;

    private int savedStatesPointer;
    private int savedStatesHead;

    private int id;

    public LayerCaretaker(int id) {
        savedStatesPointer = 0;
        savedStatesHead = 0;
        this.id = id;
        layer = new Layer();
        savedStates = new ArrayList<>();
        savedStates.add(layer.saveToMemento());
    }

    public void restorePreviousState(GraphicsContext gc) {
        savedStatesPointer--;
        layer.restoreFromMemento(savedStates.get(savedStatesPointer));
        layer.redrawLayer(gc);
    }

    public void restoreNextState(GraphicsContext gc) {
        savedStatesPointer++;
        layer.restoreFromMemento(savedStates.get(savedStatesPointer));
        layer.redrawLayer(gc);
    }

    public int getSavedStatesPointer() {
        return savedStatesPointer;
    }

    public int getSavedStatesHead() {
        return savedStatesHead;
    }

    public int getId() {
        return id;
    }

    public void addDrawableToLayer(Drawable drawable) {
        layer.addDrawable(drawable);
        reorganizeSavedStates();
        saveState();
    }

    public void redrawLayer(GraphicsContext gc) {
        layer.restoreFromMemento(savedStates.get(savedStatesPointer));
        layer.redrawLayer(gc);
    }

    private void reorganizeSavedStates() {
        for(int i = savedStatesHead; i > savedStatesPointer; i--) {
            savedStates.remove(i);
        }
    }

    private void saveState() {
        savedStatesPointer++;
        savedStatesHead = savedStatesPointer;
        savedStates.add(savedStatesPointer, layer.saveToMemento());
    }
}
