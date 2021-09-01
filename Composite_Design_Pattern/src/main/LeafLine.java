package main;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class LeafLine implements Component {

    Point start, end;

    public LeafLine(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void draw(AnchorPane canvas) {
        Line line = new Line(start.x, start.y, end.x, end.y);
        canvas.getChildren().add(line);
    }
}
