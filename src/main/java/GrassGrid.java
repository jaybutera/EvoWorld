import gameobjects.*;

public class GrassGrid extends Grid{
    private double[][] grass;
    private GroundGrid ground;

    // percent grass is determined by ideal 100% CO2, 50% moisture, 50% sunlight
    public GrassGrid() {
        grass = new double[100][100];
        ground = new GroundGrid();

        for(int x = 0; x < 100; x++) {
            for(int y = 0; y < 100; y++) {
                double percent = ground.getCo2(x, y);

                if(ground.getMoisture(x, y) > 50) {
                    percent = percent + (100 - ground.getMoisture(x, y));
                }
                else {
                    percent = percent + ground.getMoisture(x, y)*2;
                }

                if(ground.getSunlight(x, y) > 50) {
                    percent = percent + (100 - ground.getSunlight(x, y));
                }
                else {
                    percent = percent + ground.getSunlight(x, y)*2;
                }

                grass[x][y] = percent/3;
            }
        }
    }

    // accessor
    public double getGrass(StaticEntity cr) {
        int pos[] = convertCoordinates(cr);
        return grass[pos[0]][pos[1]];
    }

    public double getGrass(int length, int width) { return grass[length][width]; }

    // mutator
    public void setGrass(DynamicEntity cr, double per) {
        int pos[] = convertCoordinates(cr);
        grass[pos[0]][pos[1]] = per;
    }

    public void growGrass() {
        for(int x = 0; x < 100; x++) {
            for(int y = 0; y < 100; y++) {
                grass[x][y] = grass[x][y]*1.1;
                if (grass[x][y] > 100) {
                    grass[x][y] = 100;
                }
            }
        }
    }
}
