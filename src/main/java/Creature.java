import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import toolbox.Vector3f;
import server.messages.PBCreatureOuterClass.PBCreature;

public abstract class Creature extends DynamicEntity {
	private int id;

	/*
    public Creature(Vector3f position, float rotation, float scale) {
		super(position, rotation, scale);
	}
	*/

	public Creature (Body body, float scale) {
		super(body, scale);
	}

	public byte[] serialize () {
		// Can leave implementation empty for now
		PBCreature c_serial = PBCreature.newBuilder()
				.setId(id)
				.setX(position.x)
				.setY(position.y)
				.setR(rotation)
				.setS(scale)
				.build();

		System.out.println(c_serial.toString());

		return c_serial.toByteArray();
	}

	public abstract void action (float[] a);
    public abstract float[] observation ();
}
