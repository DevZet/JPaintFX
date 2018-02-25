package Models.Prototypes;

/**
 * Created by Dominik on 2016-12-06.
 */
public interface Transformable {

    void changeSize(double size, Drawable drawable);

    void rotate(double angle);

    void move(double horizontal, double vertical);

}
