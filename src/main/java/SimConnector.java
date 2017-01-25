import org.zeromq.ZMQ;
import sim.messages.AI.Store.*;

import java.util.ArrayList;

public class SimConnector {
    private String port = "5560";
    private ZMQ.Context context;
    private ZMQ.Socket req;

    public SimConnector () {
        context = ZMQ.context(1);

        req = context.socket(ZMQ.REQ);
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

    public void sendObservations () {

    }

    public void close () {
        req.close();
        context.term();
    }
}