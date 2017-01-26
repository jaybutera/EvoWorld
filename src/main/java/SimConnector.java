import com.google.flatbuffers.FlatBufferBuilder;
import org.zeromq.ZMQ;
//import sim.messages.AI.Obs.Creature;
import sim.messages.AI.Store.*;

import java.util.ArrayList;

public class SimConnector {
    private String port = "5560";
    private ZMQ.Context context;
    private ZMQ.Socket req;
    private FlatBufferBuilder builder;
    private int view_size;

    public SimConnector (int view_size) {
        this.view_size = view_size;

        context = ZMQ.context(1);

        req = context.socket(ZMQ.REQ);

        builder = new FlatBufferBuilder(1024);
    }

    public void connect () {
        req.connect("tcp://127.0.0.1:" + port);
        req.send("start");
    }

    public ArrayList<Integer> getIds () {
        byte[] bytes = req.recv();
        java.nio.ByteBuffer buf = java.nio.ByteBuffer.wrap(bytes);

        Ids ids_fb = Ids.getRootAsIds(buf);

        int ids_len = ids_fb.idvecLength();

        ArrayList<Integer> ids = new ArrayList<>();
        for (int i = 0; i < ids_len; i++)
            ids.add( ids_fb.idvec(i) );

        return ids;
    }

    public void sendObservations (Creature[] creatures) {
        int[] fb_creatures = new int[creatures.length];

        for (int i = creatures.length; i >= 0; i--) {
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
        //sim.messages.AI.Obs.Observations.createObsVector(builder,fb_creatures);

        // Send serialized form of Observations
        req.send( builder.sizedByteArray() );
    }

    public void close () {
        req.close();
        context.term();
    }
}