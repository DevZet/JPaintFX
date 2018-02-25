package Models.Managers;

import Models.Prototypes.Drawable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Created by Dominik on 2017-02-18.
 */
public class DrawingManager {

    public Drawable selectedDrawable;

    private Paint currentFillColor;
    private Paint currentStrokeColor;
    private int currentLineWidth;

    public void setSelectedDrawable(Drawable drawable) {
        selectedDrawable = drawable;
    }

    public void onDrawingStart(Point2D startPoint, Paint currentFillColor, Paint currentStrokeColor, int currentLineWidth) {
        selectedDrawable.setBeginCoordinates(startPoint);

        this.currentFillColor = currentFillColor;
        this.currentStrokeColor = currentStrokeColor;
        this.currentLineWidth = currentLineWidth;
    }

    public void onDrawingProceed(Point2D drawingPoint, GraphicsContext gc, boolean regularFigureSet) {
        selectedDrawable.setDrawTimeProperties(drawingPoint, regularFigureSet);

        gc.setFill(currentFillColor);
        gc.setStroke(currentStrokeColor);
        gc.setLineWidth(currentLineWidth);
        selectedDrawable.draw(gc);
    }
}
