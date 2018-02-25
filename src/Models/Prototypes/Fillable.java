package Models.Prototypes;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Dominik on 2016-12-06.
 */
public interface Fillable {

    void fill(GraphicsContext gc);

    void fillRegular(GraphicsContext gc);

}
