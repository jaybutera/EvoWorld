import org.zeromq.ZMQ;

public class SimConnector {
    private String port = "5560";
    private ZMQ.Context context;
    private ZMQ.Socket req;

    public SimConnector () {
        context = ZMQ.context(1);

        req = context.socket(ZMQ.REQ);
        req.connect("tcp://127.0.0.1:" + port);
    }

    public void close () {
        req.close();
        context.term();
    }
}