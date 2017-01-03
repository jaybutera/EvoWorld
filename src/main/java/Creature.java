import toolbox.Vector3f;
import server.messages.PBCreatureOuterClass.PBCreature;

public abstract class Creature extends DynamicEntity {
	private int id;

    public Creature(Vector3f position, Vector3f rotation, float scale) {
		super(position, rotation, scale);
		// TODO Auto-generated constructor stub
	}

	public byte[] serialize () {
		// Can leave implementation empty for now
		PBCreature c_serial = PBCreature.newBuilder()
				.setId(id)
				.setX(position.x)
				.setY(position.y)
				.build();

		System.out.println(c_serial.toString());

		return c_serial.toByteArray();
	}

	public abstract void action (float[] a);
    public abstract float[] observation ();
}
