package com.solipsis.game;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.solipsis.game.components.*;
import com.solipsis.game.managers.AssetManager;
import com.solipsis.game.systems.PhysicsDebugRenderingSystem;
import com.solipsis.game.systems.PhysicsSystem;
import com.solipsis.game.systems.PlayerControlSystem;
import com.solipsis.game.systems.RenderingSystem;

public class SlimeVolleyball extends ApplicationAdapter {

	SpriteBatch batch;
	World world;

    OrthographicCamera b2dcam;
    OrthographicCamera cam;

	private Camera camera;

    public static com.badlogic.gdx.physics.box2d.World boxWorld;

    @Override
	public void create () {


		camera = new PerspectiveCamera();

        Box2D.init();
        b2dcam = new OrthographicCamera(1000,1000);
        cam = new OrthographicCamera();
		boxWorld = new com.badlogic.gdx.physics.box2d.World(new Vector2(0,-80), true);


		batch = new SpriteBatch();
		WorldConfiguration config = new WorldConfiguration()
				.setSystem(RenderingSystem.class)
				.setSystem(PlayerControlSystem.class)
				.setSystem(PhysicsDebugRenderingSystem.class)
				.setSystem(PhysicsSystem.class);
		world = new World(config);

		int slime = world.create();
		/*
			world.edit(slime)
				.add(new Position(400,400))
				.add(new PlayerControlled())
				.add(new PhysicsBody(boxWorld, new Vector2(0,0)))
				.add(new Sprite())
				.add(new SpriteReference(AssetManager.SpriteFile.SLIME));
		*/

		int ball = world.create();
		world.edit(ball)
				.add(new Position(100, 100))
				.add(new PhysicsBody(boxWorld, new Vector2(100,500), "a"));




		int slime2 = world.create();
		world.edit(slime2)
				.add(new Position(0,0))
				.add(new PlayerControlled())
				.add(new PhysicsBody(boxWorld, new Vector2(0,300)))
				.add(new Sprite())
				.add(new SpriteReference(AssetManager.SpriteFile.SLIME));

		BodyDef groundBodyDef =new BodyDef();
        // Set its world position3
		groundBodyDef.position.set(new Vector2(10, 0));

        // Create a body from the defintion and add it to the world
		Body groundBody = boxWorld.createBody(groundBodyDef);

        // Create a polygon shape
		PolygonShape groundBox = new PolygonShape();
        // Set the polygon shape as a box which is twice the size of our view port and 20 high
        // (setAsBox takes half-width and half-height as arguments)
		groundBox.setAsBox(500, 10.0f);
        // Create a fixture from our polygon shape and add it to our ground body
		groundBody.createFixture(groundBox, 1.0f);
        // Clean up after ourselves
		groundBox.dispose();


	}



	@Override
	public void render () {

	//	debugMatrix=new Matrix4(b2dcam.combined);

//BoxObjectManager.BOX_TO_WORLD = 100f
//Scale it by 100 as our box physics bodies are scaled down by 100
	//	debugMatrix.scale(1, 1, 1f);



	//	viewport.apply();

        //debugRenderer.render(boxWorld,debugMatrix);
		world.setDelta(Gdx.graphics.getDeltaTime());
		boxWorld.step(Gdx.graphics.getDeltaTime(), 8, 3);

		world.process();
	}

	@Override
	public void resize(int width, int height) {
	//	b2dcam = new OrthographicCamera(width/2, height/2);
	//	viewport.update(width, height);
	}
}
