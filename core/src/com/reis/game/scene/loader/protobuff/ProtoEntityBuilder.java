package com.reis.game.scene.loader.protobuff;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.reis.game.entity.EntityFactory;
import com.reis.game.entity.GameEntity;
import com.reis.game.entity.template.EntityTemplate;
import com.reis.game.resource.prototype.EntityTypeProto.EntityData;

import java.lang.reflect.Constructor;
import java.util.concurrent.ExecutionException;

/**
 * Created by bernardoreis on 11/20/16.
 */

public final class ProtoEntityBuilder {

    private LoadingCache<String, EntityTemplate> cache;
    private final int MAX_CACHE_ENTRIES = 20;
    private final String ENTITY_PACKAGE = "com.reis.game.entity";
    private final String ENTITY_TEMPLATE_IMPL_PACKAGE = "com.reis.game.entity.template";

    public ProtoEntityBuilder() {
        cache = CacheBuilder
                .newBuilder()
                .maximumSize(MAX_CACHE_ENTRIES)
                .build(new CacheLoader<String, EntityTemplate>() {
                    @Override
                    public EntityTemplate load(String key) throws Exception {
                        return createEntityTemplateInstance(key);
                    }
                });
    }

    public GameEntity buildEntity(EntityData entityData) {
        try {
            EntityTemplate template = getEntityTemplate(entityData.getTemplateName());
            return EntityFactory.createFromTemplate(template, entityData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public GameEntity createEntityInstance(EntityData entityData, EntityTemplate template)
        throws Exception {
        String className = ENTITY_PACKAGE + "." + template.className;
        Class<? extends GameEntity> entityClass = (Class<? extends GameEntity>) Class.forName(className);
        Constructor<? extends GameEntity> constructor =
                entityClass.getDeclaredConstructor(Integer.TYPE, Integer.TYPE, Integer.TYPE);
        return constructor.newInstance(entityData.getId(), entityData.getRow(), entityData.getCol());
    }

    private EntityTemplate getEntityTemplate(final String className) throws ExecutionException {
        return cache.get(className);
    }

    private EntityTemplate createEntityTemplateInstance(String className) throws Exception {
        Class<? extends EntityTemplate> entityTemplateClass = getEntityTemplateClass(className);
        Constructor<? extends EntityTemplate> constructor = entityTemplateClass.getDeclaredConstructor();
        return constructor.newInstance();
    }

    private Class<? extends EntityTemplate> getEntityTemplateClass(String name) throws ClassNotFoundException {
        String className = ENTITY_TEMPLATE_IMPL_PACKAGE + "." + name;
        return (Class<? extends EntityTemplate>) Class.forName(className);
    }

}
