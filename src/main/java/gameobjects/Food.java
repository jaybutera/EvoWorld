package gameobjects;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import server.messages.PBFoodOuterClass;

public class Food extends DynamicEntity {
    int id;
    public ChemicalComposition chem;
    private FoodManager manager;

    public Food (Body body, FoodManager manager, float scale, int id) {
        super(body, scale);
        this.id = id;
        this.manager = manager;

        // Temporary test parameters
        chem = new ChemicalComposition(1f,1f,1f, 4);
    }

    public PBFoodOuterClass.PBFood serialize (int action) {
        Vec2 position = body.getPosition();
        PBFoodOuterClass.PBFood c_serial = PBFoodOuterClass.PBFood.newBuilder()
                .setId(id)
                .setX(position.x)
                .setY(position.y)
                .setAction(action)
                .build();

        return c_serial;
    }

    public EntityType getEntityType () {
        return EntityType.Food;
    }

    public void die () {
        this.manager.remove(this);
    }
}
