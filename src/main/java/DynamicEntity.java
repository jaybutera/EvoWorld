import toolbox.Vector3f;
import org.jbox2d.dynamics.Body;

public abstract class DynamicEntity extends Entity {
	final public Body body;

	public DynamicEntity(Vector3f position,
						 float rotation,
						 float scale,
						 Body body) {
		super(position, rotation, scale);

		this.body = body;
	}

	public DynamicEntity (Body body, float scale) {
		super (new Vector3f(body.getPosition().x, body.getPosition().y, 0f), body.getAngle(), scale);

		this.body = body;
	}
}
