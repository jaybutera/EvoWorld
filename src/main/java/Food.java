import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import server.messages.PBCreatureOuterClass;
import server.messages.PBFoodOuterClass;

public class Food extends DynamicEntity {
    int id;

    public Food (Body body, float scale) {
        super(body, scale);
        id = 0;
    }

    public PBFoodOuterClass.PBFood serialize () {
        Vec2 position = body.getPosition();
        PBFoodOuterClass.PBFood c_serial = PBFoodOuterClass.PBFood.newBuilder()
                .setId(id)
                .setX(position.x)
                .setY(position.y)
                .setAction(0)
                .build();

        return c_serial;
    }
}
