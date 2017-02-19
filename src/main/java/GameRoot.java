import gameobjects.Creature;
import gameobjects.Food;
import server.messages.PBCreatureOuterClass;
import server.messages.PBFoodOuterClass;
import server.messages.PBGameStateOuterClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class GameRoot {
    private ProxyConnector proxy;
    private SimConnector sim;

    private PetriWorld world;
    private boolean first_run = true;
    private long iteration;
    private long epoch_iter;
    private long start_time, end_time;

    private boolean connected = false;

    public void initialize () {
        // Connect to proxy server to update player clients of game state
        proxy = new ProxyConnector("127.0.0.1", 8000);

        // On failure to connect to proxy, continue execution
        try {
            connected = proxy.connect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Connect to simulation server to get creature actions
        sim = new SimConnector("127.0.0.1", 5559);

        // On failure to connect to sim server, crash
        try {
            sim.connect();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        // TODO: Restructure to set creature ids
        int[] ids = sim.getIds();
        world = new PetriWorld( ids );

        iteration = 0;
        epoch_iter = 1;
    }

    public void step () {
        // Next epoch criteria
        if (epoch_iter % 10000 == 0) {
            nextEpoch();
        }
        else {

            start_time = System.currentTimeMillis();
            sim.sendObservations( world.getObservations() );
            end_time = System.currentTimeMillis();

            if (iteration % 100 == 0)
                System.out.println("Elapsed send obs time (ms) - " + (end_time - start_time));

            start_time = System.currentTimeMillis();
            world.applyActions(sim.getActions());
            end_time = System.currentTimeMillis();

            if (iteration % 100 == 0)
                System.out.println("Elapsed actions time (ms) - " + (end_time - start_time));

            // Perform physical update
            world.step(iteration);

            // Update proxy server
            if (connected)
                send();

            // Check that creatures still exist
            if ( world.creatures.length == 0 )
                nextEpoch();
        }

        /* Debugging
        System.out.println("Creatures");
        for (int j = 0; j < world.creatures.length; j++)
            System.out.println(world.creatures[j].serialize().toString());
        System.out.println("gameobjects.Food [0]");
        //for (int j = 0; j < world.food.length; j++)
        System.out.println(world.food[0].serialize(0).toString());
        */

        iteration++;
    }

    private void nextEpoch () {
        int[] ids = sim.nextEpoch( world.getFitnessRecords() );

        System.out.println("New creature set: ");
        for (int i = 0; i < ids.length; i++)
            System.out.print(ids[i] + " | ");
        System.out.println("");

        world = new PetriWorld( ids );
        epoch_iter = 1;
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
        return world.foodManager.food;
    }

    private void send () {
        ArrayList<PBCreatureOuterClass.PBCreature> creatures = new ArrayList<>();
        for (Creature c : world.creatures)
            creatures.add( c.serialize() );

        ArrayList<PBFoodOuterClass.PBFood> food = new ArrayList<>();
        if (first_run) {
            first_run = false;
            for (Food c : world.foodManager.food)
                food.add(c.serialize(0));
        }
        else {
            for (Food c : world.foodManager.food)
                food.add(c.serialize(2));
        }

        try {
            proxy.send(
                    PBGameStateOuterClass.PBGameState.newBuilder()
                            .addAllCreatureStat(creatures)
                            .addAllFoodStat(food)
                            .build()
            );
        }
        catch (Exception e) {
            System.out.println("Failed to send game state to server");
            e.printStackTrace();
        }
    }
}
