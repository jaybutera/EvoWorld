/**
 * Created by jaybutera on 1/18/17.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import processing.core.PApplet;
import server.messages.PBCreatureOuterClass;
import server.messages.PBGameStateOuterClass;

public class Visualization extends PApplet {
    public void setup() {
        size(500,500);

        world = new PetriWorld(1);
        world.create();
    }

    public void draw(){
        //ellipse(200,200,50,50);

        // Perform physical update
        world.step();
        background(230);

        int x=5;
        int y=0;

        for (int j = 0; j < world.d_bodies.length; j++) {
            System.out.println(world.d_bodies[j].serialize().toString());
            fill(0,250,0);
            ellipse(world.d_bodies[j].getPosition().x, -world.d_bodies[j].getPosition().y, 50,50);
            //ellipse(mouseX, mouseY, 100,100);
        }

        try {
            TimeUnit.MILLISECONDS.sleep(20);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private PetriWorld world;
}
