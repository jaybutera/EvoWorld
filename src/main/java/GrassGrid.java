import gameobjects.*;
import org.jbox2d.common.Vec2;

public class GrassGrid {
    private double[][] grass = new double [100][100];
    private GroundGrid ground;

    public GrassGrid() {
        ground = new GroundGrid();

        for(int x = 0; x < 100; x++) {
            for(int y = 0; y < 100; y++) {
                double percent = (ground.getCo2(x, y) + ground.getMoisture(x, y))/2;
                grass[x][y] = percent;
            }
        }
    }

    // accessor
    public double getOxygen(StaticEntity cr) {
        int pos[] = convertCoordinates(cr);
        return grass[pos[0]][pos[1]];
    }

    public int[] convertCoordinates(StaticEntity cr) {
        Vec2 pos = cr.getPosition();
        int x = (int)pos.x/10;
        int y = (int)pos.y/10;
        int arr[] = {x, y};

        return arr;
    }
}
