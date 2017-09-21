#!/bin/bash
DEST=$(cd ../../../../core/src; pwd)
protoc ai.proto --java_out="$DEST"
#protoc animation.proto --java_out=."$DEST"
#protoc entity.proto --java_out=."$DEST"
protoc entityType.proto --java_out="$DEST"
#protoc overlay.proto --java_out=."$DEST"
protoc questLog.proto --java_out="$DEST"
protoc questState.proto --java_out="$DEST"
protoc reaction.proto --java_out="$DEST"
protoc requirement.proto --java_out="$DEST"
protoc screen.proto --java_out="$DEST"
#protoc screenReqs.proto --java_out=."$DEST"
protoc sprite.proto --java_out="$DEST"
#protoc tilemap.proto --java_out=."$DEST"