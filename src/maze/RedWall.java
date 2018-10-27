package maze;

import java.awt.*;

public class RedWall extends Wall {

    @Override
    public void enter() {
        System.out.println("Ran into the giant RED WALL!");
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }
}
