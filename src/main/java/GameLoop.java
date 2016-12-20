import com.jogamp.newt.Display;

public class GameLoop {
    public static void main (String[] args) {
        DisplayManager dm = new DisplayManager();
        dm.createDisplay();

        Loader load = new Loader();
        Renderer renderer = new Renderer();

        float[] v = {
                -.5f, .5f, 0f,
                -.5f, -.5f, 0f,
                .5f, -.5f, 0f,
                .5f, -.5f, 0f,
                .5f, .5f, 0f,
                -.5f, .5f, 0f
        };

        RawModel model = load.loadToVAO(v);

        while ( dm.windowShouldClose() ) {
            renderer.prepare();
            renderer.render(model);
            dm.updateDisplay();
        }

        load.cleanUp();
        dm.closeDisplay();
    }
}
