package maze;

import java.awt.*;

public class BlueWall extends Wall {

    @Override
    public void enter() {
        System.out.println("Ran into the Watery Blue Wall!!");
    }

    @Override
    public Color getColor() {
        return Color.BLUE;
    }
}
