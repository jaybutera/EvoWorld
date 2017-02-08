import gameobjects.Creature;
import org.jbox2d.common.Vec2;

public class GroundGrid {
    private double oxygen[][] = new double[500][10]; // percent of oxygen at each grid index
    private double moisture[][] = new double[500][10]; // percent of moisture at each grid index
    private double co2[][] = new double[500][10]; // percent of CO2 at each grid index

    // accessors
    public double getOxygen(Creature cr) {
        int pos[] = convertCoordinates(cr);
        return oxygen[pos[0]][pos[1]];
    }

    public double getMoisture(Creature cr) {
        int pos[] = convertCoordinates(cr);
        return moisture[pos[0]][pos[1]];
    }

    public double getCo2(Creature cr) {
        int pos[] = convertCoordinates(cr);
        return co2[pos[0]][pos[1]];
    }

    // modifiers - currently can be changed by where creature is
    public void setOxygen(Creature cr, double per) {
        int pos[] = convertCoordinates(cr);
        oxygen[pos[0]][pos[1]] = per;
    }

    public void setMoisture(Creature cr, double per) {
        int pos[] = convertCoordinates(cr);
        moisture[pos[0]][pos[1]] = per;
    }

    public void setCo2(Creature cr, double per) {
        int pos[] = convertCoordinates(cr);
        co2[pos[0]][pos[1]] = per;
    }

    // convert coordinates of where Creature is (later plant) to access and change info about position's environment
    public int[] convertCoordinates(Creature cr) {
        Vec2 pos = cr.getPosition();
        int x = (int)pos.x;
        int y = (int)pos.y;
        int arr[] = {x, y};

        return arr;
    }
}
