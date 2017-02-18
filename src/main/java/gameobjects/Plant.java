package gameobjects;

import org.jbox2d.dynamics.Body;

public class Plant extends Food {
    public Plant (Body body, FoodManager manager, float scale, int id) {
        super(body, manager, scale, id);
        this.id = id;

        // Temporary test parameters
        chem = new ChemicalComposition(1f,1f,1f, 4);
    }

    // tie in soil/ GroundGrid later

}
