import gameobjects.Creature;
import toolbox.Tuple;

import java.util.ArrayList;

public class FitnessRecords {
    public FitnessRecords () {
        creature_fits = new ArrayList<>();
    }
    private ArrayList<Tuple<Creature, Float>> creature_fits;

    public ArrayList<Tuple<Creature, Float>> getFitnessRecords () {
        return creature_fits;
    }

    public void addRecord (Creature c, float fitness) {
        creature_fits.add( new Tuple<>(c, fitness) );
    }
}
