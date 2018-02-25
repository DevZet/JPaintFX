package Models.Prototypes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Dominik on 2016-12-06.
 */
public interface Drawable {

    Drawable copyObject(Drawable drawable);

    void draw(GraphicsContext gc);

    void redraw(GraphicsContext gc);

    void setBeginCoordinates(Point2D drawingPoint);

    void setEndCoordinates(Point2D drawingPoint);

    void savePropertiesFromGraphicsContext(GraphicsContext gc);

    void setPropertiesToGraphicsContext(GraphicsContext gc);

    void setDrawTimeProperties(Point2D drawingPoint, boolean regularFigureSet);
}
