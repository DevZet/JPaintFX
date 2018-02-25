package Models.Lines;

import Models.Coordinates;
import Models.Prototypes.Drawable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Dominik on 2016-12-06.
 */
public class StraightLine extends Line{

    public StraightLine(){
    }

    private StraightLine(StraightLine another) {
        this.beginCoordinates = another.beginCoordinates;
        this.endCoordinates = another.endCoordinates;
        this.strokeColor = another.strokeColor;
        this.strokeLineWidth = another.strokeLineWidth;
    }

    @Override
    public Drawable copyObject(Drawable drawable) {
        return new StraightLine((StraightLine) drawable);
    }

    @Override
    public void stroke(GraphicsContext gc) {
        gc.strokeLine(beginCoordinates.getX(), beginCoordinates.getY(), endCoordinates.getX(), endCoordinates.getY());
    }

    @Override
    public void strokeRegular(GraphicsContext gc) {
        stroke(gc);
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
    public void changeSize(double size, Drawable drawable) {

    }

    @Override
    public void rotate(double angle) {

    }

    @Override
    public void move(double horizontal, double vertical) {

    }
}
