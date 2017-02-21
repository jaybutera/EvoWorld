package gameobjects;

import com.google.flatbuffers.FlatBufferBuilder;
import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;
import server.messages.PBCreatureOuterClass.PBCreature;

import java.util.ArrayList;

public abstract class Creature extends DynamicEntity {
	protected int id;
	protected float energy;
	public ChemicalComposition chems;
	private CreatureManager manager;

	public Creature (Body body, CreatureManager manager, float scale, int id) {
		super(body, scale);

		this.id = id;
		this.energy = 70f;
		this.manager = manager;

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

	public Vec2 getPosition () {
		return body.getPosition();
	}

	public abstract void action (float[] a);
    public abstract CreatureObservation observation (ArrayList<Fixture> foundFixtures);

    public EntityType getEntityType () {
    	return EntityType.Creature;
	}

	public void die () {
		this.manager.remove(this);
	}
}
