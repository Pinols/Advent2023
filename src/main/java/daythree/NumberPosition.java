package daythree;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class NumberPosition {
    String value="";
    List<Point> points = new ArrayList();
    boolean isValid = false;
    
    int isGear = 0;

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
