package Models;

import Models.Prototypes.Drawable;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominik on 2016-12-06.
 */
class Layer {

    private List<Drawable> drawables = new ArrayList<Drawable>();

    Layer() {
    }

    void addDrawable(Drawable drawable) {
        Drawable copy = drawable.copyObject(drawable);
        drawables.add(copy);
    }


    void redrawLayer(GraphicsContext gc) {
        for(Drawable drawable : drawables) {
            drawable.redraw(gc);
        }
    }

    Memento saveToMemento() {
        return new Memento(this.drawables);
    }

    void restoreFromMemento(Memento memento) {
        this.drawables = new ArrayList<>(memento.getSavedDrawables());
    }

    static class Memento {
        private final List<Drawable> drawables = new ArrayList<>();

        Memento(List<Drawable> drawings) {
            drawables.addAll(drawings);
        }

        private List<Drawable> getSavedDrawables() {
            return drawables;
        }
    }
}
