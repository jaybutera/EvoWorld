import server.messages.PBCreatureOuterClass;
import server.messages.PBGameStateOuterClass;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FastLoop {
    private ProxyConnector connector;
    private PetriWorld world;

    public static void main (String[] args) {
        new FastLoop().run();
    }

    public void run () {
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

        GameRoot root = new GameRoot();
        root.initialize();

        //----------------
        // Main Game Loop
        //----------------

        while (true) {
            root.timed_step();
        }

        /*
        shader.cleanUp();
        load.cleanUp();
        dm.closeDisplay();
        */
    }

    public void send () {
        ArrayList<PBCreatureOuterClass.PBCreature> creatures = new ArrayList<>();
        for (Creature c : world.creatures)
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
    }
}
