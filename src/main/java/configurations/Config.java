package configurations;

public class Config {
    public final int food_count;
    //public final int creature_count;
    public final int world_size;
    public final int fps;

    public Config (int fc, int ws, int fs) {
        food_count = fc;
        //creature_count = cc;
        world_size = ws;
        fps        = fs;
    }
}
