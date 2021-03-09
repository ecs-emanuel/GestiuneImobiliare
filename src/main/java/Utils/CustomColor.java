package Utils;

import java.awt.*;

public enum CustomColor
{
    RED_STRONG(new Color(230, 50, 50)),
    ORANGE_COMFY(new Color(238, 108, 77)),
    GREEN_STRONG(new Color(69, 200, 60)),
    GREEN_COMFY(new Color(103, 212, 68)),
    GRAY_VERYLIGHT(new Color(235, 235, 235)),
    GRAY_LIGHTSTEEL(new Color(215, 215, 215)),
    BLUE_DARKSTEEL(new Color(61, 90, 128)),
    BLUE_DARKDEEP(new Color(41, 52, 65));

    Color color;

    private CustomColor(Color color)
    {
        this.color = color;
    }

    public Color getColor()
    {
        return this.color;
    }
}
