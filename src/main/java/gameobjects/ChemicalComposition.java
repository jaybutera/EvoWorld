package gameobjects;

public class ChemicalComposition {
    private int type;
    private float strength;

    public int getType() {
        return type;
    }

    public float getStrength() {
        return strength;
    }

    public ChemicalComposition (int type, float strength) {
        this.type = type;
        this.strength = strength;
    }
}
