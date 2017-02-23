import gameobjects.*;
import java.lang.Math;

public class GroundGrid extends Grid{
    private double[][] oxygen = new double[100][100]; // percent of oxygen at each grid index
    private double[][] moisture = new double[100][100]; // percent of moisture at each grid index
    private double[][] sunlight = new double[100][100]; // percent of sunlight reaching each grid index
    private double[][] co2 = new double[100][100]; // percent of CO2 at each grid index

    public GroundGrid() {
        for(int x = 0; x < 100; x++) {
            for(int y = 0; y < 100; y++) {
                oxygen[x][y] = Math.random()*100;
                moisture[x][y] = Math.random()*100;
                sunlight[x][y] = Math.random()*100;
                co2[x][y] = Math.random()*100;
            }
        }
    }

    // accessors
    public double getOxygen(DynamicEntity cr) {
        int pos[] = convertCoordinates(cr);
        return oxygen[pos[0]][pos[1]];
    }

    public double getOxygen(int length, int width) { return oxygen[length][width]; }

    public double getMoisture(DynamicEntity cr) {
        int pos[] = convertCoordinates(cr);
        return moisture[pos[0]][pos[1]];
    }

    public double getMoisture(int length, int width) { return moisture[length][width]; }

    public double getSunlight(DynamicEntity cr) {
        int pos[] = convertCoordinates(cr);
        return sunlight[pos[0]][pos[1]];
    }

    public double getSunlight (int length, int width) { return sunlight[length][width]; }

    public double getCo2(DynamicEntity cr) {
        int pos[] = convertCoordinates(cr);
        return co2[pos[0]][pos[1]];
    }

    public double getCo2(int length, int width) { return co2[length][width]; }

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
}
