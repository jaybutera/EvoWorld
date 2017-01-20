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

        // Render
        for (int j = 0; j < creatures.length; j++) {
            System.out.println(creatures[j].serialize().toString());

            fill(0, 250, 0);
            cur_creat = creatures[j];
            ellipse(cur_creat.getPosition().x, -cur_creat.getPosition().y, cur_creat.getScale(), cur_creat.getScale());
        }
    }
}
