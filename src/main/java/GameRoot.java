import java.util.ArrayList;

abstract public class GameRoot {
    private ArrayList<FrameListener> frame_listeners;

    public GameRoot () {
        frame_listeners = new ArrayList<FrameListener>();
    }

    abstract public void run ();

    protected void start_listeners () {
        for (FrameListener f : frame_listeners)
            f.initialize();
    }

    protected void update_listeners () {
        for (FrameListener f : frame_listeners)
            f.start_frame();
    }

    public void add_frame_listener(FrameListener f) {
        frame_listeners.add(f);
    }
}
