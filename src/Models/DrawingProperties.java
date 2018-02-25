package Models;

/**
 * Created by Dominik on 2016-12-23.
 */
public class DrawingProperties {

    private double xPos;
    private double yPos;
    private double width;
    private double height;

    public double getXPos() {
        return xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void resolvePositionAndMeasures(Coordinates beginCoordinates, Coordinates endCoordinates) {
        if(endCoordinates.getX() < beginCoordinates.getX()) {
            xPos = endCoordinates.getX();
            width = beginCoordinates.getX() - endCoordinates.getX();
        } else {
            xPos = beginCoordinates.getX();
            width = endCoordinates.getX() - beginCoordinates.getX();
        }
        if(endCoordinates.getY() < beginCoordinates.getY()) {
            yPos = endCoordinates.getY();
            height = beginCoordinates.getY() - endCoordinates.getY();
        } else {
            yPos = beginCoordinates.getY();
            height = endCoordinates.getY() - beginCoordinates.getY();
        }
    }

}
