package gameobjects;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import server.messages.PBCreatureOuterClass.PBCreature;

public abstract class Creature extends DynamicEntity {
	protected int id;
	protected float energy;
	protected World world;
	public ChemicalComposition chems;

	public Creature (Body body, float scale, int id, World world) {
		super(body, scale);

		this.id = id;
		this.world = world;
		this.energy = 50f;

		chems = new ChemicalComposition(0f,0f,0f,2f);
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

	public abstract boolean action (float[] a);
    public abstract float[] observation ();
}
