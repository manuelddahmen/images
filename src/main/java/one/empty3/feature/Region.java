package one.empty3.feature;

import java.awt.*;

public class Region extends AreaDescriptor {
    public Region(int x, int y, int sizeX, int sizeY) {
        super(x, y, sizeX, sizeY);
    }

    @Override
    public FilterPixM getFilter() {
        return null;
    }

    @Override
    public double match() {
        return 0;
    }
}
