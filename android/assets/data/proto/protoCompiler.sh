# echo $(which protoc)
# DEST=$(cd ../../../../core/src; pwd)
echo "Hello world"
protoc sprite.proto --java_out=../../../../core/src
# protoc animation.proto --java_out="$DEST"
# protoc ai.proto --java_out="$DEST"
# protoc entityType.proto --java_out="$DEST"
# protoc entity.proto --java_out="$DEST"
# protoc tilemap.proto --java_out="$DEST"
# protoc overlay.proto --java_out="$DEST"
# protoc screen.proto --java_out="$DEST"
# protoc screenReqs.proto --java_out="$DEST"
# protoc questState.proto --java_out="$DEST"
