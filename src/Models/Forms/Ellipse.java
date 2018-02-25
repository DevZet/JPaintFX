package Models.Forms;

import Models.DrawingProperties;
import Models.Prototypes.Drawable;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Dominik on 2016-12-06.
 */
public class Ellipse extends Form{

    DrawingProperties drawingProperties;

    public Ellipse() {
        drawingProperties = new DrawingProperties();
    }

    private Ellipse(Ellipse another) {
        this.beginCoordinates = another.beginCoordinates;
        this.endCoordinates = another.endCoordinates;
        this.fillColor = another.fillColor;
        this.strokeColor = another.strokeColor;
        this.strokeLineWidth = another.strokeLineWidth;
        this.drawingProperties = another.drawingProperties;
        this.isRegular = another.isRegular;
        this.stroke = another.stroke;
    }

    @Override
    public Drawable copyObject(Drawable ellipse) {
        return new Ellipse((Ellipse)ellipse);
    }

    @Override
    public void stroke(GraphicsContext gc) {
        drawingProperties.resolvePositionAndMeasures(beginCoordinates, endCoordinates);

        gc.strokeOval(drawingProperties.getXPos(), drawingProperties.getYPos(), drawingProperties.getWidth(), drawingProperties.getHeight());
    }

    @Override
    public void strokeRegular(GraphicsContext gc) {
        drawingProperties.resolvePositionAndMeasures(beginCoordinates, endCoordinates);

        gc.strokeOval(drawingProperties.getXPos(), drawingProperties.getYPos(), drawingProperties.getWidth(), drawingProperties.getWidth());
    }

    @Override
    public void fill(GraphicsContext gc) {
        drawingProperties.resolvePositionAndMeasures(beginCoordinates, endCoordinates);

        gc.fillOval(drawingProperties.getXPos(), drawingProperties.getYPos(), drawingProperties.getWidth(), drawingProperties.getHeight());
    }

    @Override
    public void fillRegular(GraphicsContext gc) {
        drawingProperties.resolvePositionAndMeasures(beginCoordinates, endCoordinates);

        gc.fillOval(drawingProperties.getXPos(), drawingProperties.getYPos(), drawingProperties.getWidth(), drawingProperties.getWidth());
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
