package configurations;

public class Config {
    public final int food_count;
    public final int world_size;
    public final float timestep;

    public Config (int fc, int ws, float ts) {
        food_count = fc;
        world_size = ws;
        timestep   = ts;
    }
}
