import toolbox.Vector3f;

public abstract class Entity implements Networked {
    private Vector3f position;
    private Vector3f rotation;
    private float scale;

    public Entity (Vector3f position, Vector3f rotation, float scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public void changePos (Vector3f d_pos) {
        position.add(d_pos);
    }

    public void changeRot(Vector3f d_rot) {
        rotation.add(d_rot);
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

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
