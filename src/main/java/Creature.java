import toolbox.Vector3f;

public abstract class Creature extends DynamicEntity {
    public Creature(Vector3f position, Vector3f rotation, float scale) {
		super(position, rotation, scale);
		// TODO Auto-generated constructor stub
	}
	public abstract void action (float[] a);
    public abstract float[] observation ();
}
