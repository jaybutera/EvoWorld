import com.jogamp.newt.Display;
import shaders.StaticShader;

public class GameLoop {
    public static void main (String[] args) {
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

        while ( dm.windowShouldClose() ) {
            renderer.prepare();
            shader.start();
            renderer.render(model);
            shader.stop();
            dm.updateDisplay();
        }

        shader.cleanUp();
        load.cleanUp();
        dm.closeDisplay();
    }
}
