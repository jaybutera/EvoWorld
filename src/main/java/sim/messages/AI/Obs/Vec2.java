// automatically generated by the FlatBuffers compiler, do not modify

package sim.messages.AI.Obs;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class Vec2 extends Struct {
  public Vec2 __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public float x() { return bb.getFloat(bb_pos + 0); }
  public float y() { return bb.getFloat(bb_pos + 4); }

  public static int createVec2(FlatBufferBuilder builder, float x, float y) {
    builder.prep(4, 8);
    builder.putFloat(y);
    builder.putFloat(x);
    return builder.offset();
  }
}

