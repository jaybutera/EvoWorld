package gameobjects;

final public class CreatureObservation {
    public float[] smell;
    public int id;

    public CreatureObservation (float[] smell, int id) {
        // There are currently 3 types of smell
        assert (smell.length == 3);

        // Copy array
        this.smell = new float[smell.length];
        for (int i = 0; i < smell.length; i++)
            this.smell[i] = smell[i];

        this.id = id;
    }
}
