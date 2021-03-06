package gameobjects;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class StaticEntity extends Entity {
    final public Body body;
    float scale;

    public StaticEntity (Body body, float scale) {
        this.body = body;
        this.scale = scale;
    }

    // Accessors and mutators
    public void changePos (Vec2 d_pos) { throw new NotImplementedException(); }

    public void changeRot(float d_rot) {
        throw new NotImplementedException();
    }

    public Vec2 getPosition() {
        return body.getPosition();
    }

    public void setPosition(Vec2 position){
        throw new NotImplementedException();
    }

    public float getRotation() {
        return body.getAngle();
    }

    public void setRotation(float rotation) {
        throw new NotImplementedException();
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public EntityType getEntityType() { return EntityType.Food; }
}
