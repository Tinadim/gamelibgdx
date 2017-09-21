package com.reis.game.mechanics.collision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Created by bernardoreis on 9/2/17.
 */

public final class CollisionManager {

    private static HashSet<Collision> onGoingCollisions = new HashSet<Collision>();
    private static List<CollisionListener> collisionListeners = new ArrayList<CollisionListener>();

    private CollisionManager() {}

    public static void registerCollision(Collision collision) {
        boolean added = onGoingCollisions.add(collision);
        if (added) {
            CollisionHandler.handleCollision(collision.entity, collision.collidedWith);
            notifyListenersCollisionStarted(collision);
        }
    }

    public static void registerCollisions(Collection<Collision> collisions) {
        for (Collision collision : collisions) {
            registerCollision(collision);
        }
    }

    public static void clearCollisions() {
        onGoingCollisions.clear();
    }

    public static void addCollisionListener(CollisionListener listener) {
        collisionListeners.add(listener);
    }

    public static void removeCollisionListener(CollisionListener listener) {
        collisionListeners.remove(listener);
    }

    public static void update() {
        Iterator<Collision> iterator = onGoingCollisions.iterator();
        while (iterator.hasNext()) {
            Collision collision = iterator.next();
            if (checkCollisionEnded(collision)) {
                notifyListenersCollisionEnded(collision);
                iterator.remove();
            }
        }
    }

    private static void notifyListenersCollisionStarted(Collision collision) {
        System.out.println("Collision started");
        for (CollisionListener listener : collisionListeners) {
            if (listener.filter == null || listener.filter.test(collision)) {
                listener.onCollisionStarted(collision);
            }
        }
    }

    private static void notifyListenersCollisionEnded(Collision collision) {
        System.out.println("Collision ended");
        for (CollisionListener listener : collisionListeners) {
            if (listener.filter == null || listener.filter.test(collision)) {
                listener.onCollisionEnded(collision);
            }
        }
    }

    private static boolean checkCollisionEnded(Collision collision) {
        return !CollisionDetector.isTouching(collision.entity, collision.collidedWith);
    }
}
