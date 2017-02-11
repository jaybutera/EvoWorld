import gameobjects.*;
import org.jbox2d.common.Vec2;

public class GroundGrid {
    private double oxygen[][] = new double[50][50]; // percent of oxygen at each grid index
    private double moisture[][] = new double[50][50]; // percent of moisture at each grid index
    private double co2[][] = new double[50][50]; // percent of CO2 at each grid index

    // accessors
    public double getOxygen(DynamicEntity cr) {
        int pos[] = convertCoordinates(cr);
        return oxygen[pos[0]][pos[1]];
    }

    public double getMoisture(DynamicEntity cr) {
        int pos[] = convertCoordinates(cr);
        return moisture[pos[0]][pos[1]];
    }

    public double getCo2(DynamicEntity cr) {
        int pos[] = convertCoordinates(cr);
        return co2[pos[0]][pos[1]];
    }

    // modifiers - currently can be changed by where creature is
    public void setOxygen(DynamicEntity cr, double per) {
        int pos[] = convertCoordinates(cr);
        oxygen[pos[0]][pos[1]] = per;
    }

    public void setMoisture(DynamicEntity cr, double per) {
        int pos[] = convertCoordinates(cr);
        moisture[pos[0]][pos[1]] = per;
    }

    public void setCo2(DynamicEntity cr, double per) {
        int pos[] = convertCoordinates(cr);
        co2[pos[0]][pos[1]] = per;
    }

    // convert coordinates of where Creature is (later plant) to access and change info about position's environment
    public int[] convertCoordinates(DynamicEntity cr) {
        Vec2 pos = cr.getPosition();
        int x = (int)pos.x/10;
        int y = (int)pos.y/10;
        int arr[] = {x, y};

        return arr;
    }
}
