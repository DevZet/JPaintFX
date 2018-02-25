package Models.Managers;

import Models.LayerCaretaker;
import Models.Prototypes.Drawable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Dominik on 2016-12-26.
 */
public class LayerCaretakersManager {

    private final ObservableList<LayerCaretaker> layerCaretakerList = FXCollections.observableArrayList();

    private int layerCaretakersHead;
    private LayerCaretaker workingLayerCaretaker;

    public LayerCaretakersManager() {
        layerCaretakersHead = 0;
        workingLayerCaretaker = new LayerCaretaker(layerCaretakersHead);
        layerCaretakerList.add(workingLayerCaretaker);
    }

    public void addLayerCaretaker() {
        layerCaretakersHead++;
        workingLayerCaretaker = new LayerCaretaker(layerCaretakersHead);
        layerCaretakerList.add(workingLayerCaretaker);
    }

    public void redrawLayers(GraphicsContext gc) {
        for(LayerCaretaker layerCaretaker : layerCaretakerList) {
            layerCaretaker.redrawLayer(gc);
        }
    }

    public LayerCaretaker getWorkingLayerCaretaker() {
        return workingLayerCaretaker;
    }

    public void setWorkingLayerCaretaker(int selectedIndex) {
        workingLayerCaretaker = layerCaretakerList.get(selectedIndex);
    }

    public void removeLayerCaretaker(int index) {
        layerCaretakerList.remove(index);
    }

    public ObservableList getLayerCaretakerList() {
        return layerCaretakerList;
    }

    public int getLayerCaretakersHead() {
        return layerCaretakersHead;
    }

    public boolean isSavedStatesListHeadEqualToPointer() {
        if(workingLayerCaretaker.getSavedStatesPointer() == workingLayerCaretaker.getSavedStatesHead()) return true;
        else return false;
    }

    public boolean isSavedStatesEmpty() {
        if(workingLayerCaretaker.getSavedStatesPointer() == 0) return true;
        else return false;
    }

    public void addDrawableToCurrentLayer(Drawable drawable) {
        workingLayerCaretaker.addDrawableToLayer(drawable);
    }
}
