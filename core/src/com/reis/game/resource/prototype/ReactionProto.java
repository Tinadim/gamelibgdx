// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: reaction.proto

package com.reis.game.resource.prototype;

public final class ReactionProto {
  private ReactionProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ReactionDataOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ReactionData)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 type = 1;</code>
     */
    int getType();

    /**
     * <code>int32 entityId = 2;</code>
     */
    int getEntityId();
  }
  /**
   * Protobuf type {@code ReactionData}
   */
  public  static final class ReactionData extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:ReactionData)
      ReactionDataOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ReactionData.newBuilder() to construct.
    private ReactionData(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ReactionData() {
      type_ = 0;
      entityId_ = 0;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private ReactionData(
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
              if (!parseUnknownFieldProto3(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {

              type_ = input.readInt32();
              break;
            }
            case 16: {

              entityId_ = input.readInt32();
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
      return com.reis.game.resource.prototype.ReactionProto.internal_static_ReactionData_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.reis.game.resource.prototype.ReactionProto.internal_static_ReactionData_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.reis.game.resource.prototype.ReactionProto.ReactionData.class, com.reis.game.resource.prototype.ReactionProto.ReactionData.Builder.class);
    }

    public static final int TYPE_FIELD_NUMBER = 1;
    private int type_;
    /**
     * <code>int32 type = 1;</code>
     */
    public int getType() {
      return type_;
    }

    public static final int ENTITYID_FIELD_NUMBER = 2;
    private int entityId_;
    /**
     * <code>int32 entityId = 2;</code>
     */
    public int getEntityId() {
      return entityId_;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (type_ != 0) {
        output.writeInt32(1, type_);
      }
      if (entityId_ != 0) {
        output.writeInt32(2, entityId_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (type_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, type_);
      }
      if (entityId_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, entityId_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.reis.game.resource.prototype.ReactionProto.ReactionData)) {
        return super.equals(obj);
      }
      com.reis.game.resource.prototype.ReactionProto.ReactionData other = (com.reis.game.resource.prototype.ReactionProto.ReactionData) obj;

      boolean result = true;
      result = result && (getType()
          == other.getType());
      result = result && (getEntityId()
          == other.getEntityId());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + TYPE_FIELD_NUMBER;
      hash = (53 * hash) + getType();
      hash = (37 * hash) + ENTITYID_FIELD_NUMBER;
      hash = (53 * hash) + getEntityId();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.reis.game.resource.prototype.ReactionProto.ReactionData parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.reis.game.resource.prototype.ReactionProto.ReactionData parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.reis.game.resource.prototype.ReactionProto.ReactionData parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.reis.game.resource.prototype.ReactionProto.ReactionData parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.reis.game.resource.prototype.ReactionProto.ReactionData parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.reis.game.resource.prototype.ReactionProto.ReactionData parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.reis.game.resource.prototype.ReactionProto.ReactionData parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.reis.game.resource.prototype.ReactionProto.ReactionData parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.reis.game.resource.prototype.ReactionProto.ReactionData parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.reis.game.resource.prototype.ReactionProto.ReactionData parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.reis.game.resource.prototype.ReactionProto.ReactionData parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.reis.game.resource.prototype.ReactionProto.ReactionData parseFrom(
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
    public static Builder newBuilder(com.reis.game.resource.prototype.ReactionProto.ReactionData prototype) {
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
     * Protobuf type {@code ReactionData}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ReactionData)
        com.reis.game.resource.prototype.ReactionProto.ReactionDataOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.reis.game.resource.prototype.ReactionProto.internal_static_ReactionData_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.reis.game.resource.prototype.ReactionProto.internal_static_ReactionData_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.reis.game.resource.prototype.ReactionProto.ReactionData.class, com.reis.game.resource.prototype.ReactionProto.ReactionData.Builder.class);
      }

      // Construct using com.reis.game.resource.prototype.ReactionProto.ReactionData.newBuilder()
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
        type_ = 0;

        entityId_ = 0;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.reis.game.resource.prototype.ReactionProto.internal_static_ReactionData_descriptor;
      }

      public com.reis.game.resource.prototype.ReactionProto.ReactionData getDefaultInstanceForType() {
        return com.reis.game.resource.prototype.ReactionProto.ReactionData.getDefaultInstance();
      }

      public com.reis.game.resource.prototype.ReactionProto.ReactionData build() {
        com.reis.game.resource.prototype.ReactionProto.ReactionData result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.reis.game.resource.prototype.ReactionProto.ReactionData buildPartial() {
        com.reis.game.resource.prototype.ReactionProto.ReactionData result = new com.reis.game.resource.prototype.ReactionProto.ReactionData(this);
        result.type_ = type_;
        result.entityId_ = entityId_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
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
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.reis.game.resource.prototype.ReactionProto.ReactionData) {
          return mergeFrom((com.reis.game.resource.prototype.ReactionProto.ReactionData)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.reis.game.resource.prototype.ReactionProto.ReactionData other) {
        if (other == com.reis.game.resource.prototype.ReactionProto.ReactionData.getDefaultInstance()) return this;
        if (other.getType() != 0) {
          setType(other.getType());
        }
        if (other.getEntityId() != 0) {
          setEntityId(other.getEntityId());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.reis.game.resource.prototype.ReactionProto.ReactionData parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.reis.game.resource.prototype.ReactionProto.ReactionData) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int type_ ;
      /**
       * <code>int32 type = 1;</code>
       */
      public int getType() {
        return type_;
      }
      /**
       * <code>int32 type = 1;</code>
       */
      public Builder setType(int value) {
        
        type_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 type = 1;</code>
       */
      public Builder clearType() {
        
        type_ = 0;
        onChanged();
        return this;
      }

      private int entityId_ ;
      /**
       * <code>int32 entityId = 2;</code>
       */
      public int getEntityId() {
        return entityId_;
      }
      /**
       * <code>int32 entityId = 2;</code>
       */
      public Builder setEntityId(int value) {
        
        entityId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 entityId = 2;</code>
       */
      public Builder clearEntityId() {
        
        entityId_ = 0;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:ReactionData)
    }

    // @@protoc_insertion_point(class_scope:ReactionData)
    private static final com.reis.game.resource.prototype.ReactionProto.ReactionData DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.reis.game.resource.prototype.ReactionProto.ReactionData();
    }

    public static com.reis.game.resource.prototype.ReactionProto.ReactionData getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ReactionData>
        PARSER = new com.google.protobuf.AbstractParser<ReactionData>() {
      public ReactionData parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new ReactionData(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ReactionData> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ReactionData> getParserForType() {
      return PARSER;
    }

    public com.reis.game.resource.prototype.ReactionProto.ReactionData getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ReactionData_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ReactionData_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016reaction.proto\".\n\014ReactionData\022\014\n\004type" +
      "\030\001 \001(\005\022\020\n\010entityId\030\002 \001(\005B1\n com.reis.gam" +
      "e.resource.prototypeB\rReactionProtob\006pro" +
      "to3"
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
    internal_static_ReactionData_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ReactionData_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ReactionData_descriptor,
        new java.lang.String[] { "Type", "EntityId", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}