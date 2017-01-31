package gameobjects;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import server.messages.PBFoodOuterClass;

public class Food extends DynamicEntity {
    int id;
    public ChemicalComposition chem;

    public Food (Body body, float scale, int id) {
        super(body, scale);
        this.id = id;

        // Temporary test parameters
        chem = new ChemicalComposition(0, 4);
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
}