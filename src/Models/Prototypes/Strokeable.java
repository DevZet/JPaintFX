package Models.Prototypes;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Dominik on 2016-12-06.
 */
public interface Strokeable {

    void stroke(GraphicsContext gc);

    void strokeRegular(GraphicsContext gc);

}
