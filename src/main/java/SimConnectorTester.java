import gameobjects.Creature;

import java.util.HashMap;
import java.util.Random;

public class SimConnectorTester {
    private int num_creatures = 5;
    private int action_size = 2;

    public SimConnectorTester(String host, int port) {}

    public void connect () {}

    public int[] getIds () {
        int[] ids = new int[num_creatures];

        for (int i = 0; i < ids.length; i++)
            ids[i] = i;

        return ids;
    }

    public void sendObservations (Creature[] creatures) {}

    public HashMap<Integer, float[]> getActions () {
        HashMap<Integer, float[]> actions = new HashMap<>();

        Random r = new Random();

        // Construct random action dictionary
        for (int i = 0; i < num_creatures; i++) {
            float[] a = new float[action_size];

            for (int j = 0; j < action_size; j++)
                a[j] = r.nextFloat();

            actions.put(i, a);
        }

        return actions;
    }

    public int[] nextEpoch(FitnessRecords fr) {
        int[] fake_ids = new int[fr.getFitnessRecords().size()];

        for (int i = 0; i < fake_ids.length; i++)
            fake_ids[i] = i;

        return fake_ids;
    }

    public void close () {}
}
