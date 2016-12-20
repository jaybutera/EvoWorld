import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class Loader {
    private int dim = 3; // 2D loader

    // Keep track of all vertex lists to be rendered
    private List<Integer> vaos = new ArrayList<Integer>();
    private List<Integer> vbos = new ArrayList<Integer>();


    public RawModel loadToVAO (float[] positions) {
        int vaoID = createVAO();
        storeDataInAttributeList(0, positions);
        unbindVAO();

        return new RawModel(vaoID, positions.length / dim);
    }

    private int createVAO () {
        int vaoID = GL30.glGenVertexArrays();
        vaos.add(vaoID);
        GL30.glBindVertexArray(vaoID);

        return vaoID;
    }

    // Create a VBO
    private void storeDataInAttributeList (int attributeNumber, float[] data) {
        int vboID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);

        vbos.add(vboID);

        // Store vertex data in VBO (in VRAM)
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber, dim, GL11.GL_FLOAT, false,0,0);

        // Unbind VBO
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    private FloatBuffer storeDataInFloatBuffer (float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);

        // Ready to read
        buffer.flip();

        return buffer;
    }

    public void cleanUp () {
        // Remove vertex lists from VRAM
        for (int vao : vaos)
            GL30.glDeleteVertexArrays(vao);
        for (int vbo : vbos)
            GL15.glDeleteBuffers(vbo);
    }

    private void unbindVAO () {
        GL30.glBindVertexArray(0);
    }
}
