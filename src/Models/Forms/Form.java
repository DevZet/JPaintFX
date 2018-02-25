package Models.Forms;

import Models.Coordinates;
import Models.Prototypes.Drawable;
import Models.Prototypes.Fillable;
import Models.Prototypes.Strokeable;
import Models.Prototypes.Transformable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Created by Dominik on 21.12.16.
 */
public abstract class Form implements Drawable, Strokeable, Fillable, Transformable {

    Coordinates beginCoordinates;
    Coordinates endCoordinates;

    Paint fillColor;
    Paint strokeColor;

    double strokeLineWidth;

    boolean stroke = false;

    boolean isRegular = false;

    @Override
    public void draw(GraphicsContext gc) {
        savePropertiesFromGraphicsContext(gc);

        if(isRegular) fillRegular(gc);
        else fill(gc);
        if(stroke) {
            if(isRegular) strokeRegular(gc);
            else stroke(gc);
        }
    }

    @Override
    public void redraw(GraphicsContext gc) {
        setPropertiesToGraphicsContext(gc);

        if(isRegular) fillRegular(gc);
        else fill(gc);
        if(stroke) {
            if(isRegular) strokeRegular(gc);
            else stroke(gc);
        }
    }

    @Override
    public void savePropertiesFromGraphicsContext(GraphicsContext gc) {
        fillColor = gc.getFill();
        strokeColor = gc.getStroke();
        strokeLineWidth = gc.getLineWidth();
        if (strokeLineWidth != 1000) stroke = true;
        else stroke = false;
    }

    @Override
    public void setPropertiesToGraphicsContext(GraphicsContext gc) {
        gc.setFill(fillColor);
        gc.setStroke(strokeColor);
        gc.setLineWidth(strokeLineWidth);
    }

    @Override
    public void setBeginCoordinates(Point2D drawingPoint) {
        beginCoordinates = new Coordinates(drawingPoint.getX(), drawingPoint.getY());
    }

    @Override
    public void setEndCoordinates(Point2D drawingPoint) {
        endCoordinates = new Coordinates(drawingPoint.getX(), drawingPoint.getY());
    }

    @Override
    public void setDrawTimeProperties(Point2D drawingPoint, boolean isRegular) {
        this.setEndCoordinates(drawingPoint);
        this.setRegularityProperty(isRegular);
    }

    private void setRegularityProperty(boolean regularityProperty) {
        this.isRegular = regularityProperty;
    }
}
