package gameobjects;

import java.util.ArrayList;

public class CreatureManager {
    public Creature[] creatures;
    public ArrayList<Creature> creaturesToRemove = new ArrayList<>();
    private FitnessRecords fitnessRecords; // Used to track dead creatures and their fitness scores

    public CreatureManager () {
        fitnessRecords = new FitnessRecords();
    }

    public void initCreatures (Creature[] creatures) {
        this.creatures = creatures;
    }

    public void remove (Creature c) {
        if ( !creaturesToRemove.contains(c) ) {
            creaturesToRemove.add(c);
        }
    }

    public FitnessRecords getFitnessRecords () {
        return fitnessRecords;
    }

    public void clearCreatures() {
        Creature[] tmp_creats = new Creature[creatures.length - creaturesToRemove.size()];

        int j = 0;
        for (int i = 0; i < creatures.length; i++) {
            if (!creaturesToRemove.contains(creatures[i])) {
                tmp_creats[j] = creatures[i];
                j++;
            }
        }

        this.creatures = tmp_creats;
        creaturesToRemove = new ArrayList<>();
    }
}
