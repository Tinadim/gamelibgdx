package com.reis.game.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentType;
import com.badlogic.ashley.utils.Bag;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.reis.game.contants.GameConstants;
import com.reis.game.contants.SceneConstants;
import com.reis.game.entity.components.EntityComponent;
import com.reis.game.mechanics.TileEntityMap;
import com.reis.game.scene.dialog.DialogManager;
import com.reis.game.util.MapUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bernardoreis on 11/17/16.
 */

public class GameEntity extends Group implements GameConstants, SceneConstants {

    protected final int id;
    protected int row, col;
    protected int tileWidth, tileHeight;
    protected boolean collidable = true;
    protected boolean interactive = true;

    protected Bag<EntityComponent> components;
    private Array<EntityComponent> componentsArray;
    private ImmutableArray<EntityComponent> immutableComponentsArray;

    private List<Vector2> hotspots;

    protected String dialog;

    public GameEntity(int id, int col, int row) {
        this.components = new Bag<EntityComponent>(16);
        this.componentsArray = new Array<EntityComponent>(false, 16);
        this.immutableComponentsArray = new ImmutableArray<EntityComponent>(componentsArray);
        this.id = id;
        this.dialog = DialogManager.getDialogForEntity(this);
        this.setCoordinates(col, row);
    }

    public <T extends EntityComponent> T getComponent (Class<? extends Component> componentClass) {
        return getComponent(ComponentType.getFor(componentClass));
    }

    private <T extends EntityComponent> T getComponent (ComponentType componentType) {
        int componentTypeIndex = componentType.getIndex();
        if (componentTypeIndex < components.getCapacity()) {
            return (T) components.get(componentType.getIndex());
        } else {
            return null;
        }
    }

    public ImmutableArray<EntityComponent> getComponents () {
        return immutableComponentsArray;
    }

    public boolean hasComponent(Class<? extends Component> componentClass) {
        return getComponent(componentClass) != null;
    }

    public void add (EntityComponent component) {
        addInternal(component);
    }

    public EntityComponent remove (Class<? extends EntityComponent> componentClass) {
        ComponentType componentType = ComponentType.getFor(componentClass);
        int componentTypeIndex = componentType.getIndex();
        EntityComponent removeComponent = components.get(componentTypeIndex);
        return removeComponent;
    }

    private boolean addInternal (EntityComponent component) {
        Class<? extends EntityComponent> componentClass = component.getClass();
        EntityComponent oldComponent = getComponent(componentClass);

        if (component == oldComponent) {
            return false;
        }

        if (oldComponent != null) {
            removeInternal(componentClass);
        }

        int componentTypeIndex = ComponentType.getIndexFor(componentClass);
        components.set(componentTypeIndex, component);
        componentsArray.add(component);

        return true;
    }

    boolean removeInternal (Class<? extends EntityComponent> componentClass) {
        ComponentType componentType = ComponentType.getFor(componentClass);
        int componentTypeIndex = componentType.getIndex();
        EntityComponent removeComponent = components.get(componentTypeIndex);

        if (removeComponent != null) {
            components.set(componentTypeIndex, null);
            componentsArray.removeValue(removeComponent, true);

            return true;
        }

        return false;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        for(EntityComponent component : getComponents())
            component.update(this, delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for(EntityComponent component : getComponents())
            component.draw(this, batch, parentAlpha);
    }

    public List<Vector2> getHotspots() {
        if (hotspots != null)
            return hotspots;

        hotspots = new ArrayList<Vector2>(2 * (tileWidth + tileHeight));
        float x = getX();
        float y = getY();

        for(int i = 0; i <= tileWidth; i++) {
            x += i * TILE_SIZE;
            if (i == tileWidth)
                x--;
            hotspots.add(new Vector2(x, y));
        }
        for(int i = 1; i <= tileHeight; i++) {
            y += i * TILE_SIZE;
            if (i == tileHeight)
                y--;
            hotspots.add(new Vector2(x, y));
        }
        for(int i = 1; i <= tileWidth; i++) {
            x -= i * TILE_SIZE;
            if (i == tileWidth)
                x++;
            hotspots.add(new Vector2(x, y));
        }
        for(int i = 1; i < tileHeight; i++) {
            y -= i * TILE_SIZE;
            if (i == tileHeight)
                y++;
            hotspots.add(new Vector2(x, y));
        }
        return hotspots;
    }

    public void invalidateHotspots() {
        this.hotspots = null;
    }

    public void bindTiles() {
        List<Vector2> hotspots = getHotspots();
        for (Vector2 spot : hotspots) {
            int row = MapUtils.toTileCoord(spot.y);
            int col = MapUtils.toTileCoord(spot.x);
            TileEntityMap.addEntityToTile(this, col, row);
        }
    }

    public void unbindTiles() {
        List<Vector2> hotspots = getHotspots();
        for (Vector2 spot : hotspots) {
            int row = MapUtils.toTileCoord(spot.y);
            int col = MapUtils.toTileCoord(spot.x);
            TileEntityMap.removeEntityFromTile(this, col, row);
        }
    }

    @Override
    public void positionChanged() {
        invalidateHotspots();
    }

    private void calcPosition(int col, int row) {
        this.setPosition(col * TILE_SIZE, row * TILE_SIZE);
    }

    public Vector2 getPosition() {
        return new Vector2(this.getX(), this.getY());
    }

    private void calcSize(int tileWidth, int tileHeight) {
        this.setSize(tileWidth * TILE_SIZE, tileHeight * TILE_SIZE);
    }

    public void interact() {

    }

    public int getId() {
        return id;
    }

    public float getCenterX() {
        return this.getWidth() * .5f + this.getX();
    }

    public float getCenterY() {
        return this.getHeight() * .5f + this.getY();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setCoordinates(int col, int row) {
        this.col = col;
        this.row = row;
        this.calcPosition(col, row);
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileSize(int tileWidth, int tileHeight) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.calcSize(tileWidth, tileHeight);
    }

    public boolean isCollidable() {
        return collidable;
    }

    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }

    public boolean isInteractive() {
        return interactive;
    }

    public void setInteractive(boolean interactive) {
        this.interactive = interactive;
    }
}
