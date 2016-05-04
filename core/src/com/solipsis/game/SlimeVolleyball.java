package com.solipsis.game;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.solipsis.game.components.*;
import com.solipsis.game.managers.AssetManager;
import com.solipsis.game.systems.PhysicsSystem;
import com.solipsis.game.systems.PlayerControlSystem;
import com.solipsis.game.systems.RenderingSystem;

public class SlimeVolleyball extends ApplicationAdapter {
	SpriteBatch batch;

	World world;
    Box2DDebugRenderer debugRenderer;
    OrthographicCamera b2dcam;
    OrthographicCamera cam;

    com.badlogic.gdx.physics.box2d.World boxWorld;

    private static final float PPM = 100;
    private static final int V_WIDTH = 400;
    private static final int V_HEIGHT = 200;


    @Override
	public void create () {


        Box2D.init();
        b2dcam = new OrthographicCamera();
        cam = new OrthographicCamera();
        debugRenderer = new Box2DDebugRenderer();
		batch = new SpriteBatch();
		WorldConfiguration config = new WorldConfiguration()
				.setSystem(RenderingSystem.class)
				.setSystem(PlayerControlSystem.class)
				.setSystem(PhysicsSystem.class);
		world = new World(config);
		boxWorld = new com.badlogic.gdx.physics.box2d.World(new Vector2(0,-10), true);
		int slime = world.create();
		/*
			world.edit(slime)
				.add(new Position(400,400))
				.add(new PlayerControlled())
				.add(new Box2dPhysics(boxWorld, new Vector2(0,0)))
				.add(new Sprite())
				.add(new SpriteReference(AssetManager.SpriteFile.SLIME));
		*/

		int slime2 = world.create();
		world.edit(slime2)
				.add(new Position(200,200))
				.add(new PlayerControlled())
				.add(new Box2dPhysics(boxWorld, new Vector2(200,200)))
				.add(new Sprite())
				.add(new SpriteReference(AssetManager.SpriteFile.SLIME));

		BodyDef groundBodyDef =new BodyDef();
        // Set its world position
		groundBodyDef.position.set(new Vector2(10, 10));

        // Create a body from the defintion and add it to the world
		Body groundBody = boxWorld.createBody(groundBodyDef);

        // Create a polygon shape
		PolygonShape groundBox = new PolygonShape();
        // Set the polygon shape as a box which is twice the size of our view port and 20 high
        // (setAsBox takes half-width and half-height as arguments)
		groundBox.setAsBox(1000, 10.0f);
        // Create a fixture from our polygon shape and add it to our ground body
		groundBody.createFixture(groundBox, 1.0f);
        // Clean up after ourselves
		groundBox.dispose();


	}



	@Override
	public void render () {
        debugRenderer.render(boxWorld, b2dcam.combined);
		world.setDelta(Gdx.graphics.getDeltaTime());
		boxWorld.step(Gdx.graphics.getDeltaTime(), 8, 3);

		world.process();


	}
}
