import com.google.flatbuffers.FlatBufferBuilder;
import org.zeromq.ZMQ;
import sim.messages.AI.Control.Actions;
import sim.messages.AI.Control.Move;
import sim.messages.AI.Store.Ids;

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

        builder = new FlatBufferBuilder(1024);
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

    public void sendObservations (Creature[] creatures) {
        int[] fb_creatures = new int[creatures.length];

        for (int i = creatures.length-1; i >= 0; i--) {
            // Build the fb observation vector
            int fb_view_offset = sim.messages.AI.Obs.Creature.createViewVector(builder, creatures[i].observation() );
            // Build fb creature
            fb_creatures[i] = sim.messages.AI.Obs.Creature.createCreature(builder, creatures[i].getId(), fb_view_offset);
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
        byte[] bytes = req.recv();
        java.nio.ByteBuffer buf = java.nio.ByteBuffer.wrap(bytes);

        Actions actions_fb = Actions.getRootAsActions(buf);

        int actions_length = actions_fb.actionLength();

        HashMap<Integer, float[]> actions = new HashMap<>();

        /***** NEEDS OPTIMIZATION ******/
        for (int i = 0; i < actions_length; i++) {
            Move m = actions_fb.action(i);

            // Tmp storage for each action vector
            float[] a = new float[actions_fb.action(0).outputLength()];

            // Build action vector
            for (int j = 0; j < m.outputLength(); j++)
                a[j] = m.output(j);

            // Store in dictionary
            actions.put(m.id(), a);
        }

        return actions;
    }

    public void close () {
        req.close();
        context.term();
    }
}