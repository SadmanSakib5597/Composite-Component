package main;

import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class Composite implements Component {

    ArrayList<Component> components = new ArrayList<>();

    public void addComponent(Component com) {
        components.add(com);
    }

    @Override
    public void draw(AnchorPane canvas) {
        for (Component com : components) {
            com.draw(canvas);
        }
    }
}
