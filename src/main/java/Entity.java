import toolbox.Vector3f;

public abstract class Entity implements Networked {
    protected Vector3f position;
    protected float rotation;
    protected float scale;

    public Entity (Vector3f position, float rotation, float scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public void changePos (Vector3f d_pos) {
        position.add(d_pos);
    }

    public void changeRot(float d_rot) {
        rotation = (rotation + d_rot) % 1.0f;
    }

    // --------------------
    // Accessors & Mutators
    // --------------------

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
