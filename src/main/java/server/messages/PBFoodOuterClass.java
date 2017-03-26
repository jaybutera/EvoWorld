// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PBFood.proto

package server;

public final class PBFoodOuterClass {
  private PBFoodOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface PBFoodOrBuilder extends
      // @@protoc_insertion_point(interface_extends:server.messages.PBFood)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required uint32 id = 1;</code>
     */
    boolean hasId();
    /**
     * <code>required uint32 id = 1;</code>
     */
    int getId();

    /**
     * <pre>
     * Position
     * </pre>
     *
     * <code>required float x = 2;</code>
     */
    boolean hasX();
    /**
     * <pre>
     * Position
     * </pre>
     *
     * <code>required float x = 2;</code>
     */
    float getX();

    /**
     * <code>required float y = 3;</code>
     */
    boolean hasY();
    /**
     * <code>required float y = 3;</code>
     */
    float getY();

    /**
     * <code>required uint32 action = 4;</code>
     */
    boolean hasAction();
    /**
     * <code>required uint32 action = 4;</code>
     */
    int getAction();
  }
  /**
   * Protobuf type {@code server.messages.PBFood}
   */
  public  static final class PBFood extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:server.messages.PBFood)
      PBFoodOrBuilder {
    // Use PBFood.newBuilder() to construct.
    private PBFood(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private PBFood() {
      id_ = 0;
      x_ = 0F;
      y_ = 0F;
      action_ = 0;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private PBFood(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              id_ = input.readUInt32();
              break;
            }
            case 21: {
              bitField0_ |= 0x00000002;
              x_ = input.readFloat();
              break;
            }
            case 29: {
              bitField0_ |= 0x00000004;
              y_ = input.readFloat();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              action_ = input.readUInt32();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return PBFoodOuterClass.internal_static_server_messages_PBFood_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return PBFoodOuterClass.internal_static_server_messages_PBFood_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              PBFoodOuterClass.PBFood.class, PBFoodOuterClass.PBFood.Builder.class);
    }

    private int bitField0_;
    public static final int ID_FIELD_NUMBER = 1;
    private int id_;
    /**
     * <code>required uint32 id = 1;</code>
     */
    public boolean hasId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required uint32 id = 1;</code>
     */
    public int getId() {
      return id_;
    }

    public static final int X_FIELD_NUMBER = 2;
    private float x_;
    /**
     * <pre>
     * Position
     * </pre>
     *
     * <code>required float x = 2;</code>
     */
    public boolean hasX() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * Position
     * </pre>
     *
     * <code>required float x = 2;</code>
     */
    public float getX() {
      return x_;
    }

    public static final int Y_FIELD_NUMBER = 3;
    private float y_;
    /**
     * <code>required float y = 3;</code>
     */
    public boolean hasY() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required float y = 3;</code>
     */
    public float getY() {
      return y_;
    }

    public static final int ACTION_FIELD_NUMBER = 4;
    private int action_;
    /**
     * <code>required uint32 action = 4;</code>
     */
    public boolean hasAction() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required uint32 action = 4;</code>
     */
    public int getAction() {
      return action_;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasX()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasY()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasAction()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeUInt32(1, id_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeFloat(2, x_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeFloat(3, y_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeUInt32(4, action_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(1, id_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeFloatSize(2, x_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeFloatSize(3, y_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(4, action_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof PBFoodOuterClass.PBFood)) {
        return super.equals(obj);
      }
      PBFoodOuterClass.PBFood other = (PBFoodOuterClass.PBFood) obj;

      boolean result = true;
      result = result && (hasId() == other.hasId());
      if (hasId()) {
        result = result && (getId()
            == other.getId());
      }
      result = result && (hasX() == other.hasX());
      if (hasX()) {
        result = result && (
            java.lang.Float.floatToIntBits(getX())
            == java.lang.Float.floatToIntBits(
                other.getX()));
      }
      result = result && (hasY() == other.hasY());
      if (hasY()) {
        result = result && (
            java.lang.Float.floatToIntBits(getY())
            == java.lang.Float.floatToIntBits(
                other.getY()));
      }
      result = result && (hasAction() == other.hasAction());
      if (hasAction()) {
        result = result && (getAction()
            == other.getAction());
      }
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      if (hasId()) {
        hash = (37 * hash) + ID_FIELD_NUMBER;
        hash = (53 * hash) + getId();
      }
      if (hasX()) {
        hash = (37 * hash) + X_FIELD_NUMBER;
        hash = (53 * hash) + java.lang.Float.floatToIntBits(
            getX());
      }
      if (hasY()) {
        hash = (37 * hash) + Y_FIELD_NUMBER;
        hash = (53 * hash) + java.lang.Float.floatToIntBits(
            getY());
      }
      if (hasAction()) {
        hash = (37 * hash) + ACTION_FIELD_NUMBER;
        hash = (53 * hash) + getAction();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static PBFoodOuterClass.PBFood parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static PBFoodOuterClass.PBFood parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static PBFoodOuterClass.PBFood parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static PBFoodOuterClass.PBFood parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static PBFoodOuterClass.PBFood parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static PBFoodOuterClass.PBFood parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static PBFoodOuterClass.PBFood parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static PBFoodOuterClass.PBFood parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static PBFoodOuterClass.PBFood parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static PBFoodOuterClass.PBFood parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(PBFoodOuterClass.PBFood prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code server.messages.PBFood}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:server.messages.PBFood)
        PBFoodOuterClass.PBFoodOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return PBFoodOuterClass.internal_static_server_messages_PBFood_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return PBFoodOuterClass.internal_static_server_messages_PBFood_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                PBFoodOuterClass.PBFood.class, PBFoodOuterClass.PBFood.Builder.class);
      }

      // Construct using PBFoodOuterClass.PBFood.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        id_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        x_ = 0F;
        bitField0_ = (bitField0_ & ~0x00000002);
        y_ = 0F;
        bitField0_ = (bitField0_ & ~0x00000004);
        action_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return PBFoodOuterClass.internal_static_server_messages_PBFood_descriptor;
      }

      public PBFoodOuterClass.PBFood getDefaultInstanceForType() {
        return PBFoodOuterClass.PBFood.getDefaultInstance();
      }

      public PBFoodOuterClass.PBFood build() {
        PBFoodOuterClass.PBFood result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public PBFoodOuterClass.PBFood buildPartial() {
        PBFoodOuterClass.PBFood result = new PBFoodOuterClass.PBFood(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.id_ = id_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.x_ = x_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.y_ = y_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.action_ = action_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof PBFoodOuterClass.PBFood) {
          return mergeFrom((PBFoodOuterClass.PBFood)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(PBFoodOuterClass.PBFood other) {
        if (other == PBFoodOuterClass.PBFood.getDefaultInstance()) return this;
        if (other.hasId()) {
          setId(other.getId());
        }
        if (other.hasX()) {
          setX(other.getX());
        }
        if (other.hasY()) {
          setY(other.getY());
        }
        if (other.hasAction()) {
          setAction(other.getAction());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        if (!hasId()) {
          return false;
        }
        if (!hasX()) {
          return false;
        }
        if (!hasY()) {
          return false;
        }
        if (!hasAction()) {
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        PBFoodOuterClass.PBFood parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (PBFoodOuterClass.PBFood) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int id_ ;
      /**
       * <code>required uint32 id = 1;</code>
       */
      public boolean hasId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required uint32 id = 1;</code>
       */
      public int getId() {
        return id_;
      }
      /**
       * <code>required uint32 id = 1;</code>
       */
      public Builder setId(int value) {
        bitField0_ |= 0x00000001;
        id_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required uint32 id = 1;</code>
       */
      public Builder clearId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        id_ = 0;
        onChanged();
        return this;
      }

      private float x_ ;
      /**
       * <pre>
       * Position
       * </pre>
       *
       * <code>required float x = 2;</code>
       */
      public boolean hasX() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <pre>
       * Position
       * </pre>
       *
       * <code>required float x = 2;</code>
       */
      public float getX() {
        return x_;
      }
      /**
       * <pre>
       * Position
       * </pre>
       *
       * <code>required float x = 2;</code>
       */
      public Builder setX(float value) {
        bitField0_ |= 0x00000002;
        x_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Position
       * </pre>
       *
       * <code>required float x = 2;</code>
       */
      public Builder clearX() {
        bitField0_ = (bitField0_ & ~0x00000002);
        x_ = 0F;
        onChanged();
        return this;
      }

      private float y_ ;
      /**
       * <code>required float y = 3;</code>
       */
      public boolean hasY() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required float y = 3;</code>
       */
      public float getY() {
        return y_;
      }
      /**
       * <code>required float y = 3;</code>
       */
      public Builder setY(float value) {
        bitField0_ |= 0x00000004;
        y_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required float y = 3;</code>
       */
      public Builder clearY() {
        bitField0_ = (bitField0_ & ~0x00000004);
        y_ = 0F;
        onChanged();
        return this;
      }

      private int action_ ;
      /**
       * <code>required uint32 action = 4;</code>
       */
      public boolean hasAction() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>required uint32 action = 4;</code>
       */
      public int getAction() {
        return action_;
      }
      /**
       * <code>required uint32 action = 4;</code>
       */
      public Builder setAction(int value) {
        bitField0_ |= 0x00000008;
        action_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required uint32 action = 4;</code>
       */
      public Builder clearAction() {
        bitField0_ = (bitField0_ & ~0x00000008);
        action_ = 0;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:server.messages.PBFood)
    }

    // @@protoc_insertion_point(class_scope:server.messages.PBFood)
    private static final PBFoodOuterClass.PBFood DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new PBFoodOuterClass.PBFood();
    }

    public static PBFoodOuterClass.PBFood getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<PBFood>
        PARSER = new com.google.protobuf.AbstractParser<PBFood>() {
      public PBFood parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new PBFood(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<PBFood> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<PBFood> getParserForType() {
      return PARSER;
    }

    public PBFoodOuterClass.PBFood getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_server_messages_PBFood_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_server_messages_PBFood_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014PBFood.proto\022\017server.messages\":\n\006PBFoo" +
      "d\022\n\n\002id\030\001 \002(\r\022\t\n\001x\030\002 \002(\002\022\t\n\001y\030\003 \002(\002\022\016\n\006a" +
      "ction\030\004 \002(\r"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_server_messages_PBFood_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_server_messages_PBFood_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_server_messages_PBFood_descriptor,
        new java.lang.String[] { "Id", "X", "Y", "Action", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
