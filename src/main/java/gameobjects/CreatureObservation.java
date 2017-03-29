package gameobjects;

final public class CreatureObservation {
    final public float[] smell;
    final public int id;
    final public float[] accel;
    final public float ang_accel;

    public CreatureObservation (float[] smell, final float[] accel, final float ang_accel, int id) {
        // There are currently 3 types of smell
        assert (smell.length == 3);

        // Copy array
        this.smell = new float[smell.length];
        for (int i = 0; i < smell.length; i++)
            this.smell[i] = smell[i];

        this.accel = accel;
        this.ang_accel = ang_accel;

        this.id = id;
    }

    public String toString () {
        String str = "";

        //str += "smell: \n";
        for (int i = 0; i < smell.length; i++)
            str += String.valueOf( smell[i] ) + ", ";

        // Add linear acceleration
        str += "Accel: (" + this.accel[0] + ", " + this.accel[1] + ") | ang accel: " + ang_accel;

        return str;
    }
}
