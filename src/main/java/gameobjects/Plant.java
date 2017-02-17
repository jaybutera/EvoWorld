package gameobjects;

import org.jbox2d.dynamics.Body;

public class Plant extends Food {
    public Plant (Body body, float scale, int id) {
        super(body, scale, id);
        this.id = id;

        // Temporary test parameters
        chem = new ChemicalComposition(1f,1f,1f, 4);
    }
    // public EntityType getEntityType () {  return EntityType.Plant;    }

}
