package Models;

import Models.Prototypes.Drawable;
import Models.Prototypes.Fillable;
import Models.Prototypes.Transformable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Dominik on 2016-12-06.
 */
public class Text implements Drawable, Transformable, Fillable {

    private String value;

    private int size;

    private Color FillColor;

    protected Coordinates beginCoordinates;

    @Override
    public void draw(GraphicsContext gc) {
        savePropertiesFromGraphicsContext(gc);

        fill(gc);
    }

    @Override
    public void redraw(GraphicsContext gc) {

    }

    @Override
    public void setBeginCoordinates(Point2D drawingPoint) {

    }

    @Override
    public void setEndCoordinates(Point2D drawingPoint) {

    }

    @Override
    public void savePropertiesFromGraphicsContext(GraphicsContext gc) {

    }

    @Override
    public void setPropertiesToGraphicsContext(GraphicsContext gc) {

    }

    @Override
    public void setDrawTimeProperties(Point2D drawingPoint, boolean regularFigureSet) {

    }

    @Override
    public Drawable copyObject(Drawable drawable) {
        return null;
    }

    @Override
    public void changeSize(double size, Drawable drawable) {

    }

    @Override
    public void rotate(double angle) {

    }

    @Override
    public void move(double horizontal, double vertical) {

    }

    @Override
    public void fill(GraphicsContext gc) {
        gc.fillText(value, beginCoordinates.getX(), beginCoordinates.getY());
    }

    @Override
    public void fillRegular(GraphicsContext gc) {

    }
}
