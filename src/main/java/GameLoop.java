import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;
import server.messages.PBCreatureOuterClass;
import server.messages.PBGameStateOuterClass;
import shaders.StaticShader;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GameLoop {
    public static void main (String[] args) {
        /*
        DisplayManager dm = new DisplayManager();
        dm.createDisplay();

        Loader load = new Loader();
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();

        float[] v = {
                -.5f, .5f, 0f,
                -.5f, -.5f, 0f,
                .5f, -.5f, 0f,
                .5f, .5f, 0f
        };

        int[] indices = {
                0,1,3,
                3,1,2
        };

        RawModel model = load.loadToVAO(v, indices);
        */
        PetriWorld world = new PetriWorld(1);
        world.create();

        // Connect to proxy server to update player clients of game state
        ProxyConnector connector = new ProxyConnector("127.0.0.1", 8000);
        try {
            connector.connect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //----------------
        // Main Game Loop
        //----------------

        //while ( dm.windowShouldClose() ) {
        for (int i = 0; i < 100; i++)
        {
            // Perform physical update
            world.step();

            // Update proxy server
            ArrayList<PBCreatureOuterClass.PBCreature> creatures = new ArrayList<>();
            for (Creature c : world.d_bodies)
                creatures.add( c.serialize() );

            try {
                connector.send(
                        PBGameStateOuterClass.PBGameState.newBuilder()
                                .addAllCreatureStat(creatures)
                                //.addAllFoodStat(null)
                                .build()
                );
            }
            catch (Exception e) {
                System.out.println("Failed to send game state to server");
                e.printStackTrace();
            }

            for (int j = 0; j < world.d_bodies.length; j++)
                System.out.println(world.d_bodies[j].serialize().toString());
                //System.out.println(world.d_bodies[j].body.getPosition().x + ", " + world.d_bodies[j].body.getPosition().y);
            /*
            renderer.prepare();
            shader.start();
            renderer.render(model);
            shader.stop();
            dm.updateDisplay();
            */

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            }
            catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        /*
        shader.cleanUp();
        load.cleanUp();
        dm.closeDisplay();
        */
    }
}
