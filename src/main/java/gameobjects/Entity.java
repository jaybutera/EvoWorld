package gameobjects;

import org.jbox2d.common.Vec2;
import toolbox.Vector3f;

public abstract class Entity /* implements gameobjects.Networked */ {
    abstract public void changePos (Vec2 d_pos);

    abstract public void changeRot(float d_rot);

    // --------------------
    // Accessors & Mutators
    // --------------------

    abstract public Vec2 getPosition();

    abstract public void setPosition(Vec2 position);

    abstract public float getRotation();

    abstract public void setRotation(float rotation);

    abstract public float getScale();

    abstract public void setScale(float scale);
}
