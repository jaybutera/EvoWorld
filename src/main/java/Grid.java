import gameobjects.Entity;
import org.jbox2d.common.Vec2;

public abstract class Grid {

    public int[] convertCoordinates(Entity cr) {
        Vec2 pos = cr.getPosition();
        int x = (int)pos.x/10;
        int y = (int)pos.y/10;
        int arr[] = {x, y};

        return arr;
    }
}
