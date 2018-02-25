package Models.Lines;

import Models.Coordinates;
import Models.Prototypes.Drawable;
import Models.Prototypes.Strokeable;
import Models.Prototypes.Transformable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Created by Dominik on 2016-12-23.
 */
public abstract class Line implements Drawable, Transformable, Strokeable {

    Coordinates beginCoordinates;
    Coordinates endCoordinates;

    Paint strokeColor;

    double strokeLineWidth;

    @Override
    public void savePropertiesFromGraphicsContext(GraphicsContext gc) {
        strokeColor = gc.getStroke();
        strokeLineWidth = gc.getLineWidth();
    }

    @Override
    public void setPropertiesToGraphicsContext(GraphicsContext gc) {
        gc.setStroke(strokeColor);
        gc.setLineWidth(strokeLineWidth);
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (gc.getLineWidth() == 1000) gc.setLineWidth(1);
        savePropertiesFromGraphicsContext(gc);

        stroke(gc);
    }

    @Override
    public void redraw(GraphicsContext gc) {
        setPropertiesToGraphicsContext(gc);

        stroke(gc);
    }

    @Override
    public void setDrawTimeProperties(Point2D drawingPoint, boolean isRegular) {
        this.setEndCoordinates(drawingPoint);
    }

}
