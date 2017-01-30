import gameobjects.Creature;
import gameobjects.Food;
import processing.core.PApplet;

public class Visualization extends PApplet {
    GameRoot root;

    public void setup() {
        root = new GameRoot();
        root.initialize();

        size(500,500);
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
            ellipse(cur_creat.getPosition().x, cur_creat.getPosition().y, cur_creat.getScale(), cur_creat.getScale());
        }
        rectMode(CENTER);
        for (int j = 0; j < food.length; j++) {
            fill(0, 0, 250);
            cur_food = food[j];

            float x = cur_food.getPosition().x;
            float y = cur_food.getPosition().y;
            float scale = cur_food.getScale();

            rect(x, y, scale, scale);
        }
    }
}
