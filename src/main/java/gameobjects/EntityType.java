package gameobjects;

public enum EntityType {
    Creature(0x0001),
    Food(0x0002),
    Wall(0x0003),
    CreatureMouthSensor(0x0004);

    private int categoryBit;

    EntityType (int categoryBit) {
        this.categoryBit = categoryBit;
    }

    public int getCategoryBit () {
        return categoryBit;
    }
}
