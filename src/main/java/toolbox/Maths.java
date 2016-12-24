package toolbox;

import org.lwjgl.ovr.OVRMatrix4f;
import org.lwjgl.ovr.OVRVector3f;

public class Maths {
    public static Matrix4f createTransformationMatrix (Vector3f translation,
                                                          float rx,
                                                          float ry,
                                                          float rz,
                                                          float scale) {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
    }
}
