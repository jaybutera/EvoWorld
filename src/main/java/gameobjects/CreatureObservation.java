package gameobjects;

final public class CreatureObservation {
    final public float[] smell;
    final public int id;
    final public float[] accel;

    public CreatureObservation (float[] smell, final float[] accel, int id) {
        // There are currently 3 types of smell
        assert (smell.length == 3);

        // Copy array
        this.smell = new float[smell.length];
        for (int i = 0; i < smell.length; i++)
            this.smell[i] = smell[i];

        this.accel = accel;

        this.id = id;
    }
}
