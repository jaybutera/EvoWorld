// automatically generated by the FlatBuffers compiler, do not modify

package sim.messages.AI.Control;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class Actions extends Table {
  public static Actions getRootAsActions(ByteBuffer _bb) { return getRootAsActions(_bb, new Actions()); }
  public static Actions getRootAsActions(ByteBuffer _bb, Actions obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public Actions __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public Move action(int j) { return action(new Move(), j); }
  public Move action(Move obj, int j) { int o = __offset(4); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int actionLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }

  public static int createActions(FlatBufferBuilder builder,
      int actionOffset) {
    builder.startObject(1);
    Actions.addAction(builder, actionOffset);
    return Actions.endActions(builder);
  }

  public static void startActions(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addAction(FlatBufferBuilder builder, int actionOffset) { builder.addOffset(0, actionOffset, 0); }
  public static int createActionVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startActionVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endActions(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishActionsBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
}

