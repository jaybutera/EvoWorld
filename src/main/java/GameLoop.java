import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;
import shaders.StaticShader;

public class GameLoop {
    public static void main (String[] args) {
        /*
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
        */
        TestWorld world = new TestWorld(2);
        world.create();

        //while ( dm.windowShouldClose() ) {
        for (int i = 0; i < 100; i++)
        {
            world.step();

            for (int j = 0; j < world.d_bodies.length; j++)
                System.out.println(world.d_bodies[j].);
                //System.out.println(world.d_bodies[j].getPosition().x + ", " + world.d_bodies[j].getPosition().y);
            /*
            renderer.prepare();
            shader.start();
            renderer.render(model);
            shader.stop();
            dm.updateDisplay();
            */
        }

        /*
        shader.cleanUp();
        load.cleanUp();
        dm.closeDisplay();
        */
    }
}
