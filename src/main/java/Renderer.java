import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.common.Color3f;
import org.jbox2d.common.IViewportTransform;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;


public class Renderer extends DebugDraw {
    public void drawSolidPolygon (Vec2[] vertices, int vertexCount, final Color3f color) {}
    public void drawPoint (Vec2 argPoint, float argRadiusOnScreen, Color3f argColor) {}
    public void drawCircle (Vec2 center, float radius, Color3f color) {}
    public void drawSegment(Vec2 p1, Vec2 p2, Color3f color) {}
    public void drawSolidCircle(Vec2 center, float radius, Vec2 axis, Color3f color) {}
    public void drawString(float x, float y, String s, Color3f color) {}
    public void drawTransform(Transform xf) {}

    //Renderer (IViewportTransform viewport) { super(viewport); }
}
