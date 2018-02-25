package Models.Lines;

import Models.Prototypes.Drawable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominik on 2016-12-27.
 */
public class Pencil extends Line {

    private List<Double> yPoints = new ArrayList<>();
    private List<Double> xPoints = new ArrayList<>();

    public Pencil() {}

    private Pencil(Pencil another) {
        this.xPoints = another.xPoints;
        this.yPoints = another.yPoints;
        this.strokeColor = another.strokeColor;
        this.strokeLineWidth = another.strokeLineWidth;
    }

    @Override
    public void stroke(GraphicsContext gc) {
        double[] xPoints = new double[this.xPoints.size()];
        double[] yPoints = new double[this.yPoints.size()];

        for(int i = 0; i < this.xPoints.size(); i++) {
            xPoints[i] = this.xPoints.get(i);
        }

        for(int i = 0; i < this.yPoints.size(); i++) {
            yPoints[i] = this.yPoints.get(i);
        }
        gc.strokePolyline(xPoints, yPoints, this.xPoints.size());
    }

    @Override
    public void strokeRegular(GraphicsContext gc) {
        stroke(gc);
    }

    @Override
    public Drawable copyObject(Drawable pencilLine) {
        return new Pencil((Pencil) pencilLine);
    }

    @Override
    public void setBeginCoordinates(Point2D drawingPoint) {
        yPoints = new ArrayList<>();
        xPoints = new ArrayList<>();
        xPoints.add(0, drawingPoint.getX());
        yPoints.add(0, drawingPoint.getY());
    }

    @Override
    public void setEndCoordinates(Point2D drawingPoint) {
        xPoints.add(drawingPoint.getX());
        yPoints.add(drawingPoint.getY());
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
