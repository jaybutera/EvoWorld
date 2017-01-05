package toolbox;

public class Maths {
    public static Matrix4f createTransformationMatrix (Vector3f translation,
                                                          float rx,
                                                          float ry,
                                                          float rz,
                                                          float scale) {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();

        matrix.translate(rx, ry, rz);

        matrix.rotate((float)Math.toRadians(rx), 1,0,0);
        matrix.rotate((float)Math.toRadians(rx), 0,1,0);
        matrix.rotate((float)Math.toRadians(rx), 0,0,1);

        matrix.scale(scale,scale,scale);

        return matrix;
    }
}
