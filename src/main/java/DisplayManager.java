import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class DisplayManager {
    // Configuration parameters
    public static final int WIDTH = 1280;
    public static final int HEIGHT = WIDTH / 2;

    private long window;

    private static GLFWErrorCallback errorCallback
            = GLFWErrorCallback.createPrint(System.err);

    /**
     * This key callback will check if ESC is pressed and will close the window
     * if it is pressed.
     */
    private static GLFWKeyCallback keyCallback = new GLFWKeyCallback() {

        @Override
        public void invoke(long window, int key, int scancode, int action, int mods) {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
                glfwSetWindowShouldClose(window, true);
            }
        }
    };

    public void createDisplay ()  {
        /* Set the error callback */
        glfwSetErrorCallback(errorCallback);

        /* Initialize GLFW */
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Create window
        window = glfwCreateWindow(WIDTH, HEIGHT, "Simple example", NULL, NULL);
        if (window == NULL) {
            glfwTerminate();
            throw new RuntimeException("Failed to create the GLFW window");
        }

        /* Center the window on screen */
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window,
                (vidMode.width() - WIDTH) / 2,
                (vidMode.height() - HEIGHT) / 2
        );

        // Setup OpenGL context
        glfwMakeContextCurrent(window);
        GL.createCapabilities();

        /* Enable vertical synchronization */
        glfwSwapInterval(1);

        /* Set the key callback */
        glfwSetKeyCallback(window, keyCallback);
    }

    public boolean windowShouldClose () {
        return !glfwWindowShouldClose(window);
    }

    public void updateDisplay () {
        /* Declare buffers for using inside the loop */
        /*
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);

        while (!glfwWindowShouldClose(window)) {

            float ratio;

            // Get width and height to calcualte the ratio
            glfwGetFramebufferSize(window, width, height);
            ratio = width.get() / (float) height.get();

            // Rewind buffers for next get
            width.rewind();
            height.rewind();

            // Set viewport and clear screen
            glViewport(0, 0, width.get(), height.get());
            glClear(GL_COLOR_BUFFER_BIT);

            // Set ortographic projection
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(-ratio, ratio, -1f, 1f, 1f, -1f);
            glMatrixMode(GL_MODELVIEW);

            // Rotate matrix
            glLoadIdentity();
            glRotatef((float) glfwGetTime() * 50f, 0f, 0f, 1f);

            // Render triangle
            glBegin(GL_TRIANGLES);
            glColor3f(1f, 0f, 0f);
            glVertex3f(-0.6f, -0.4f, 0f);
            glColor3f(0f, 1f, 0f);
            glVertex3f(0.6f, -0.4f, 0f);
            glColor3f(0f, 0f, 1f);
            glVertex3f(0f, 0.6f, 0f);
            glEnd();

            // Swap buffers and poll Events
            glfwSwapBuffers(window);
            glfwPollEvents();

            // Flip buffers for next loop
            width.flip();
            height.flip();
        }

        glfwSwapBuffers(window);
        glfwPollEvents();
        */

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        glfwSwapBuffers(window); // swap the color buffers

        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();

    }

    public void closeDisplay () {
        glfwTerminate();
    }
}
