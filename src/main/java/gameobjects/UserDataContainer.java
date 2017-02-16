package gameobjects;

public final class UserDataContainer {
    public final EntityType entityType;
    public final Entity entity;

    public UserDataContainer (EntityType entityType, Entity entity) {
        this.entityType = entityType;
        this.entity = entity;
    }
}
