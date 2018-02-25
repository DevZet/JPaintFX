package Models.Forms;

import Models.Prototypes.Drawable;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Dominik on 2016-12-06.
 */
public class Triangle extends Form {

    public Triangle() {
    }

    private Triangle(Triangle another) {
        this.beginCoordinates = another.beginCoordinates;
        this.endCoordinates = another.endCoordinates;
        this.fillColor = another.fillColor;
        this.strokeColor = another.strokeColor;
        this.strokeLineWidth = another.strokeLineWidth;
        this.stroke = another.stroke;
    }

    @Override
    public Drawable copyObject(Drawable triangle) {
        return new Triangle((Triangle)triangle);
    }

    @Override
    public void stroke(GraphicsContext gc) {
        gc.strokePolygon(new double[]{beginCoordinates.getX(), endCoordinates.getX(), beginCoordinates.getX() + ((endCoordinates.getX() - beginCoordinates.getX())/2.0)},
                new double[]{beginCoordinates.getY(), beginCoordinates.getY(), endCoordinates.getY()}, 3);
    }

    @Override
    public void strokeRegular(GraphicsContext gc) {
        stroke(gc);
    }

    @Override
    public void fill(GraphicsContext gc) {
        gc.fillPolygon(new double[]{beginCoordinates.getX(), endCoordinates.getX(), beginCoordinates.getX() + ((endCoordinates.getX() - beginCoordinates.getX())/2.0)},
                new double[]{beginCoordinates.getY(), beginCoordinates.getY(), endCoordinates.getY()}, 3);
    }

    @Override
    public void fillRegular(GraphicsContext gc) {
        fill(gc);
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
