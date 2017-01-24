// automatically generated by the FlatBuffers compiler, do not modify

package sim.messages.AI.Control;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class Move extends Table {
  public static Move getRootAsMove(ByteBuffer _bb) { return getRootAsMove(_bb, new Move()); }
  public static Move getRootAsMove(ByteBuffer _bb, Move obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public Move __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int id() { int o = __offset(4); return o != 0 ? bb.getShort(o + bb_pos) & 0xFFFF : 0; }
  public float output(int j) { int o = __offset(6); return o != 0 ? bb.getFloat(__vector(o) + j * 4) : 0; }
  public int outputLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer outputAsByteBuffer() { return __vector_as_bytebuffer(6, 4); }

  public static int createMove(FlatBufferBuilder builder,
      int id,
      int outputOffset) {
    builder.startObject(2);
    Move.addOutput(builder, outputOffset);
    Move.addId(builder, id);
    return Move.endMove(builder);
  }

  public static void startMove(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addId(FlatBufferBuilder builder, int id) { builder.addShort(0, (short)id, 0); }
  public static void addOutput(FlatBufferBuilder builder, int outputOffset) { builder.addOffset(1, outputOffset, 0); }
  public static int createOutputVector(FlatBufferBuilder builder, float[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addFloat(data[i]); return builder.endVector(); }
  public static void startOutputVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endMove(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}

