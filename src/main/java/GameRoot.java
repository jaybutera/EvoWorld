import server.messages.PBCreatureOuterClass;
import server.messages.PBGameStateOuterClass;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GameRoot {
    private ProxyConnector connector;
    private PetriWorld world;

    private boolean connected = false;

    public void initialize () {
        world = new PetriWorld(1);
        world.create();

        // Connect to proxy server to update player clients of game state
        connector = new ProxyConnector("127.0.0.1", 8000);

        try {
            connected = connector.connect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void step () {
        // Perform physical update
        world.step();

        // Update proxy server
        if (connected)
            send();

        for (int j = 0; j < world.creatures.length; j++)
            System.out.println(world.creatures[j].serialize().toString());
    }

    public void timed_step () {
        step();

        try {
            TimeUnit.MILLISECONDS.sleep(20);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public Creature[] getCreatures () {
        return world.creatures;
    }

    public Food[] getFood () {
        return world.food;
    }

    private void send () {
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
