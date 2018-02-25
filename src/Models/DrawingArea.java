package Models;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Dominik on 2016-12-14.
 */
public class DrawingArea extends Canvas {

    public DrawingArea() {
    }

    public void resetDrawingArea(GraphicsContext gc) {
        gc.clearRect(0, 0, this.getWidth(), this.getHeight());
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return getHeight();
    }

}
