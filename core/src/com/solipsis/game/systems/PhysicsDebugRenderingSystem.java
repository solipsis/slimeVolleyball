package com.solipsis.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.solipsis.game.SlimeVolleyball;
import com.solipsis.game.components.PhysicsBody;

/**
 * Created by dave on 5/4/2016.
 */
public class PhysicsDebugRenderingSystem extends EntityProcessingSystem {

    Box2DDebugRenderer debugRenderer;
    World boxWorld;
    ComponentMapper<PhysicsBody> pm;
    SpriteBatch spriteBatch;

    public PhysicsDebugRenderingSystem() {
        super (Aspect.all(PhysicsBody.class));
        System.out.println("adding debug renderer");
        this.boxWorld = SlimeVolleyball.boxWorld;
        debugRenderer = new Box2DDebugRenderer();
        spriteBatch = new SpriteBatch();
    }

    @Override
    protected void process(Entity e) {
        spriteBatch.begin();
        Gdx.gl.glClearColor(1,0,0,1);
     //   Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(boxWorld, SlimeVolleyball.camera.combined);
        spriteBatch.end();

    }


}
