import com.google.flatbuffers.FlatBufferBuilder;
import gameobjects.Creature;
import gameobjects.CreatureObservation;
import gameobjects.FitnessRecords;
import org.zeromq.ZMQ;
import sim.messages.AI.Obs.*;
import sim.messages.AI.Control.*;
import sim.messages.AI.Control.Move;
import sim.messages.AI.Store.Ids;
import toolbox.Tuple;

import java.util.ArrayList;
import java.util.HashMap;

public class SimConnector {
    private ZMQ.Context context;
    private ZMQ.Socket req;
    private FlatBufferBuilder builder;

    private String host;
    private int port;

    public SimConnector (String host, int port) {
        this.host = host;
        this.port = port;

        context = ZMQ.context(1);

        req = context.socket(ZMQ.REQ);

        builder = new FlatBufferBuilder(2048);
    }

    public void resetBuilder (int size) {
        builder = new FlatBufferBuilder(size);
    }

    public void connect () {
        req.connect("tcp://" + host + ":" + port);
        req.send("start");
    }

    public int[] getIds () {
        byte[] bytes = req.recv();
        java.nio.ByteBuffer buf = java.nio.ByteBuffer.wrap(bytes);

        Ids ids_fb = Ids.getRootAsIds(buf);

        int ids_len = ids_fb.idvecLength();

        int[] ids = new int[ids_len];
        for (int i = 0; i < ids_len; i++)
            ids[i] = ids_fb.idvec(i);

        return ids;
    }

    public void sendObservations (CreatureObservation[] creatureObservations) {
        int[] fb_creatures = new int[creatureObservations.length];

        for (int i = creatureObservations.length-1; i >= 0; i--) {
            // Build the fb observation vector
            //int fb_view_offset = sim.messages.AI.Obs.FBCreature.createViewVector(builder, creatures[i].observation() );
            CreatureObservation obs = creatureObservations[i];
            //int fb_smell_offset = sim.messages.AI.Obs.Smell.createSmell(builder, obs[0], obs[1], obs[2] );
            // Build fb creature
            FBCreature.startFBCreature (builder);
            FBCreature.addId(builder, creatureObservations[i].id );
            FBCreature.addSmell(builder, Smell.createSmell(builder, obs.smell[0], obs.smell[1], obs.smell[2]) );
            FBCreature.addAccel(builder, Vec2.createVec2(builder, obs.accel[0], obs.accel[0]));
            FBCreature.addAngAccel(builder, creatureObservations[i].ang_accel);
            fb_creatures[i] = FBCreature.endFBCreature(builder);

        }

        int fb_obs_offset = sim.messages.AI.Obs.Observations.createObsVector(builder, fb_creatures);

        // Construct top level Observations message. Contains list of Creatures.
        sim.messages.AI.Obs.Observations.startObservations(builder);
        sim.messages.AI.Obs.Observations.addObs(builder, fb_obs_offset);
        int obs = sim.messages.AI.Obs.Observations.endObservations(builder);
        builder.finish(obs);

        // Send serialized form of Observations
        req.send( builder.sizedByteArray() );
    }

    public HashMap<Integer, float[]> getActions () {
        //long a1 = System.currentTimeMillis();
        byte[] bytes = req.recv();
        //long b = System.currentTimeMillis();

        //System.out.println("Action recv (ms) - " + (a1 - b));

        // Setup
        //-----------------
        java.nio.ByteBuffer buf = java.nio.ByteBuffer.wrap(bytes);
        Actions actions_fb = Actions.getRootAsActions(buf);
        HashMap<Integer, float[]> actions = new HashMap<>();

        int num_actions = actions_fb.actionLength();

        /***** NEEDS OPTIMIZATION ******/
        // Tmp storage for each action vector
        float[] a ;//= new float[actions_fb.action(0).outputLength()];

        // Map flat buffer to dictionary (hashmap)
        //-----------------------------------
        for (int i = 0; i < num_actions; i++) {
            Move m = actions_fb.action(i);
            a = new float[actions_fb.action(0).outputLength()];

            // Build action vector
            for (int j = 0; j < m.outputLength(); j++)
                a[j] = m.output(j);

            // Store in dictionary
            actions.put(m.id(), a);
        }

        return actions;
    }

    public int[] nextEpoch (FitnessRecords fitnessRecords) {
        ArrayList<Tuple<Creature, Float>> fr = fitnessRecords.getFitnessRecords();

        // Epoch command
        req.send("epoch");
        req.recv();

        // Send fitness scores
        //------------------------------------------------

        int[] scores = new int[fr.size()];

        System.out.println("Fitness scores");
        for (int i = 0; i < fr.size(); i++) {
            Tuple<Creature, Float> record = fr.get(i);
            scores[i] = Score.createScore(builder, record.x.getId(), record.y);
            System.out.println(record.x.getId() + " | " + record.y);
        }

        int scores_offset = Epoch.createScoresVector(builder, scores);
        //Epoch epoch = Epoch.createEpoch(builder, scores_offset);

        Epoch.startEpoch(builder);
        Epoch.addScores(builder, scores_offset);
        int epoch_offset = Epoch.endEpoch(builder);
        builder.finish(epoch_offset);

        req.send( builder.sizedByteArray() );
        //------------------------------------------------


        // Return next set of ids
        return getIds();
    }

    public void close () {
        req.close();
        context.term();
    }
}