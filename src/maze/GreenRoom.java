package maze;

import java.awt.*;

public class GreenRoom extends Room {
    public GreenRoom(int num) {
        super(num);
    }



    @Override
    public Color getColor() {
        return Color.GREEN;
    }
}
