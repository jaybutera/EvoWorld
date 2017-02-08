package gameobjects;

public class ChemicalComposition {
    private float protein;
    private float starch;
    private float fat;

    private float strength;

    public float getProtein() {
        return protein;
    }

    public float getStarch() {
        return starch;
    }

    public float getFat() {
        return fat;
    }

    public float getStrength() {
        return strength;
    }

    public ChemicalComposition (float p, float s, float f, float strength) {
        this.protein = p;
        this.fat = fat;
        this.starch = starch;

        this.strength = strength;
    }
}
