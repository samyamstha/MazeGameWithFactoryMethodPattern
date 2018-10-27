package maze;

import java.awt.*;

public class RedRoom extends Room {

    public static final Color LIGHT_RED = new Color(255, 102,102);
    public RedRoom(int num) {
        super(num);
    }


    @Override
    public Color getColor() {
        return LIGHT_RED;
    }
}
