import gameobjects.Creature;
import gameobjects.Food;
import processing.core.PApplet;

public class Visualization extends PApplet {
    GameRoot root;
    private float resRatio;
    private int screenSize = 500;
    private int worldSize;

    private float scaleSize (float scaleSize) {
        return (screenSize * scaleSize) / worldSize;
    }

    public void setup() {
        root = new GameRoot();
        root.initialize();
        worldSize = root.world.worldSize;

        size(screenSize,screenSize);
        resRatio = (float) screenSize / (float) worldSize;
    }

    public void draw(){
        root.timed_step();
        background(230);

        root.step();

        Creature[] creatures = root.getCreatures();
        Creature cur_creat;
        Food[] food = root.getFood();
        Food cur_food;

        // Render
        ellipseMode(RADIUS);
        for (int j = 0; j < creatures.length; j++) {
            fill(0, 250, 0);
            cur_creat = creatures[j];

            ellipse(cur_creat.getPosition().x * resRatio,
                    cur_creat.getPosition().y * resRatio,
                    scaleSize( cur_creat.getScale() ),
                    scaleSize( cur_creat.getScale() )
            );
        }

        rectMode(CENTER);
        for (int j = 0; j < food.length; j++) {
            fill(0, 0, 250);
            cur_food = food[j];

            float scale = cur_food.getScale();

            rect(cur_food.getPosition().x * resRatio,
                    cur_food.getPosition().y * resRatio,
                    scaleSize( scale ),
                    scaleSize( scale )
            );
        }
    }
}
