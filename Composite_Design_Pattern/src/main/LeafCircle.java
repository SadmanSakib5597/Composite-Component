package main;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class LeafCircle implements Component {

    Point start;
    double radius;

    public LeafCircle(Point start, double radius) {
        this.start = start;
        this.radius = radius;
    }

    @Override
    public void draw(AnchorPane canvas) {
        Circle circle = new Circle(start.x + radius, start.y + radius, radius);
        canvas.getChildren().add(circle);
    }
}
