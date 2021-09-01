package main;

import com.sun.xml.internal.ws.developer.SchemaValidation;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

import java.net.URL;
import java.util.ResourceBundle;

class Point {
    double x = 0;
    double y = 0;

    public Point() {

    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

public class Controller implements Initializable {

    @FXML
    AnchorPane canvas;
    @FXML
    Button lineButton, triangleButton, rectangleButton, circleButton, starButton, flagButton;

    Point start, end;

    boolean isLine, isTriangle, isRectangle, isCircle, isStar, isFlag;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setDefaultColorOfButton();

        canvas.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
                    start = new Point(mouseEvent.getX(), mouseEvent.getY());
                }

                if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED) {
                    end = new Point(mouseEvent.getX(), mouseEvent.getY());
                    draw();
                }
            }
        });

    }

    private void setDefaultColorOfButton() {
        lineButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        triangleButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        rectangleButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        circleButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        starButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        flagButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
    }


    public void drawLine(Point start, Point end) {
        Line line = new Line(start.x, start.y, end.x, end.y);
        canvas.getChildren().add(line);
    }

    public void draw() {

        canvas.getChildren().clear();

        if (isLine) {
            Component line = new LeafLine(start, end);
            line.draw(canvas);
        } else if (isTriangle) {
            double diameter = Math.sqrt(((start.x - end.x) * (start.x - end.x)) + ((start.y - end.y) * (start.y - end.y)));
            double radius = diameter / 2.0;

            Point a = start;
            Point b = new Point(a.x, a.y + radius);
            Point c = new Point(end.x, end.y);

            Component line1 = new LeafLine(a, b);
            Component line2 = new LeafLine(b, c);
            Component line3 = new LeafLine(c, a);


            Composite triangle = new Composite();
            triangle.addComponent(line1);
            triangle.addComponent(line2);
            triangle.addComponent(line3);

            triangle.draw(canvas);

        } else if (isRectangle) {
            double diameter = Math.sqrt(((start.x - end.x) * (start.x - end.x)) + ((start.y - end.y) * (start.y - end.y)));
            double radius = diameter / 2.0;

            Point a = start;
            Point b = new Point(a.x, a.y + radius);
            Point c = new Point(end.x, b.y);
            Point d = new Point(c.x, start.y);

            Component line1 = new LeafLine(a, b);
            Component line2 = new LeafLine(b, c);
            Component line3 = new LeafLine(c, d);
            Component line4 = new LeafLine(d, a);

            Composite rectangle = new Composite();

            rectangle.addComponent(line1);
            rectangle.addComponent(line2);
            rectangle.addComponent(line3);
            rectangle.addComponent(line4);

            rectangle.draw(canvas);


        } else if (isCircle) {
            double diameter = Math.sqrt(((start.x - end.x) * (start.x - end.x)) + ((start.y - end.y) * (start.y - end.y)));
            double radius = diameter / 2.0;

            Component circle = new LeafCircle(start, radius);
            circle.draw(canvas);

        } else if (isFlag) {

            double diameter = Math.sqrt(((start.x - end.x) * (start.x - end.x)) + ((start.y - end.y) * (start.y - end.y)));
            double radius = diameter / 2.0;

            Point a = start;
            Point b = new Point(a.x, a.y + radius);
            Point c = new Point(end.x, b.y);
            Point d = new Point(c.x, start.y);

            Component line1 = new LeafLine(a, b);
            Component line2 = new LeafLine(b, c);
            Component line3 = new LeafLine(c, d);
            Component line4 = new LeafLine(d, a);

            diameter = Math.sqrt(((start.x - end.x) * (start.x - end.x)) + ((start.y - end.y) * (start.y - end.y)));
            radius = diameter / 8.0;

            Component circle = new LeafCircle(new Point(start.x + radius, start.y + radius), radius);


            Composite flag = new Composite();

            flag.addComponent(line1);
            flag.addComponent(line2);
            flag.addComponent(line3);
            flag.addComponent(line4);
            flag.addComponent(circle);

            flag.draw(canvas);



        } else if (isStar) {
            double diameter = Math.sqrt(((start.x - end.x) * (start.x - end.x)) + ((start.y - end.y) * (start.y - end.y)));
            double radius = diameter / 2.0;

            double[] points = {55 + radius, 0 + radius, 67 + radius, 36 + radius, 109 + radius, 36 + radius,
                    73 + radius, 54 + radius, 83 + radius, 96 + radius, 55 + radius, 72 + radius, 27 + radius, 96 + radius, 37 + radius, 54 + radius,
                    1 + radius, 36 + radius, 43 + radius, 36 + radius};

            Polygon star = new Polygon(points);

            canvas.getChildren().add(star);
        }

    }

    public void setLine() {
        isLine = true;
        isTriangle = false;
        isRectangle = false;
        isCircle = false;
        isStar = false;
        isFlag = false;

        lineButton.setBackground(new Background(new BackgroundFill(Color.web("#607d8b"), CornerRadii.EMPTY, Insets.EMPTY)));
        triangleButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        rectangleButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        circleButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        starButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        flagButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void setTriangle() {
        isLine = false;
        isTriangle = true;
        isRectangle = false;
        isCircle = false;
        isStar = false;
        isFlag = false;

        lineButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        triangleButton.setBackground(new Background(new BackgroundFill(Color.web("#607d8b"), CornerRadii.EMPTY, Insets.EMPTY)));
        rectangleButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        circleButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        starButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        flagButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void setRectangle() {
        isLine = false;
        isTriangle = false;
        isRectangle = true;
        isCircle = false;
        isStar = false;
        isFlag = false;

        lineButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        triangleButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        rectangleButton.setBackground(new Background(new BackgroundFill(Color.web("#607d8b"), CornerRadii.EMPTY, Insets.EMPTY)));
        circleButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        starButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        flagButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void setCircle() {
        isLine = false;
        isTriangle = false;
        isRectangle = false;
        isCircle = true;
        isStar = false;
        isFlag = false;

        lineButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        triangleButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        rectangleButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        circleButton.setBackground(new Background(new BackgroundFill(Color.web("#607d8b"), CornerRadii.EMPTY, Insets.EMPTY)));
        starButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        flagButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void setStar() {
        isLine = false;
        isTriangle = false;
        isRectangle = false;
        isCircle = false;
        isStar = true;
        isFlag = false;

        lineButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        triangleButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        rectangleButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        circleButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        flagButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        starButton.setBackground(new Background(new BackgroundFill(Color.web("#607d8b"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void setFlag() {
        isLine = false;
        isTriangle = false;
        isRectangle = false;
        isCircle = false;
        isStar = false;
        isFlag = true;

        lineButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        triangleButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        rectangleButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        circleButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
        flagButton.setBackground(new Background(new BackgroundFill(Color.web("#607d8b"), CornerRadii.EMPTY, Insets.EMPTY)));
        starButton.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));
    }


}
