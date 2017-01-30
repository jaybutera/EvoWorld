package gameobjects;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import server.messages.PBCreatureOuterClass.PBCreature;

public abstract class Creature extends DynamicEntity {
	protected int id;

	public Creature (Body body, float scale, int id) {
		super(body, scale);

		this.id = id;
	}

	public PBCreature serialize () {
		Vec2 position = body.getPosition();
		PBCreature c_serial = PBCreature.newBuilder()
				.setId(id)
				.setX(position.x)
				.setY(position.y)
				.setR(body.getAngle())
				.setS(scale)
				.build();

		return c_serial;
	}

	public int getId() {
		return id;
	}

	public abstract void action (float[] a);
    public abstract byte[] observation ();
}
