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
import com.solipsis.game.systems.*;

public class SlimeVolleyball extends ApplicationAdapter {

	SpriteBatch batch;
	World world;

	int WORLD_HEIGHT = 100;
	int WORLD_WIDTH = 100;

    public static float DEGTORAD  = 0.0174532925199432957f;

	public static Camera camera;

    public static com.badlogic.gdx.physics.box2d.World boxWorld;

    @Override
	public void create () {




        Box2D.init();
		boxWorld = new com.badlogic.gdx.physics.box2d.World(new Vector2(0,-10), true);

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(100, 100 * (h/w));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();


		batch = new SpriteBatch();
		WorldConfiguration config = new WorldConfiguration()
				.setSystem(RenderingSystem.class)
				.setSystem(PlayerControlSystem.class)
				.setSystem(PhysicsDebugRenderingSystem.class)
				.setSystem(PhysicsSystem.class)
				.setSystem(BallSystem.class)
				.setSystem(SlimeSystem.class);
		world = new World(config);

	//	int slime = world.create();
		/*
			world.edit(slime)
				.add(new Position(400,400))
				.add(new PlayerControlled())
				.add(new PhysicsBody(boxWorld, new Vector2(0,0)))
				.add(new Sprite())
				.add(new SpriteReference(AssetManager.SpriteFile.SLIME));
		*/

        int slimeHeight = 7;
        int slimeWidth = 7;


		int ball = world.create();
		world.edit(ball)
				.add(new Position(50, 50))
				.add(new PhysicsBody(boxWorld, new Vector2(50, 50), "a"))
				.add(new Ball());


		int slime = world.create();
		world.edit(slime)
				.add(new Position(20,6))
				.add(new PlayerControlled(1))
				.add(new PhysicsBody(boxWorld, new Vector2(20,6)))
				.add(new Sprite(slimeWidth*2, slimeHeight*2))
				.add(new Slime())
				.add(new SpriteReference(AssetManager.SpriteFile.SLIME));

		int slime2 = world.create();
		world.edit(slime2)
				.add(new Position(50,20))
				.add(new PlayerControlled(2))
				.add(new PhysicsBody(boxWorld, new Vector2(70,6)))
				.add(new Sprite(slimeWidth*2, slimeHeight*2))
				.add(new Slime())
				.add(new SpriteReference(AssetManager.SpriteFile.SLIME));

		BodyDef groundBodyDef =new BodyDef();
        // Set its world position3
		groundBodyDef.position.set(new Vector2(50, 1));
        // Create a body from the defintion and add it to the world
		Body groundBody = boxWorld.createBody(groundBodyDef);
        // Create a polygon shape
		PolygonShape groundBox = new PolygonShape();
        // (setAsBox takes half-width and half-height as arguments)
		groundBox.setAsBox(47,3);
        // Create a fixture from our polygon shape and add it to our ground body
		groundBody.createFixture(groundBox, 0);
        // Clean up after ourselves
		groundBox.dispose();

        BodyDef groundBodyDef2 =new BodyDef();
        // Set its world position3
        groundBodyDef2.position.set(new Vector2(1, 0));
        // Create a body from the defintion and add it to the world
        Body groundBody2 = boxWorld.createBody(groundBodyDef2);
        // Create a polygon shape
        PolygonShape groundBox2 = new PolygonShape();
        // (setAsBox takes half-width and half-height as arguments)
        groundBox2.setAsBox(3,100);
        // Create a fixture from our polygon shape and add it to our ground body
        groundBody2.createFixture(groundBox2, 0);
        // Clean up after ourselves
        groundBox2.dispose();

        BodyDef groundBodyDef3 =new BodyDef();
        // Set its world position3
        groundBodyDef3.position.set(new Vector2(97, 10));
        // Create a body from the defintion and add it to the world
        Body groundBody3 = boxWorld.createBody(groundBodyDef3);
        // Create a polygon shape
        PolygonShape groundBox3 = new PolygonShape();
        // (setAsBox takes half-width and half-height as arguments)
        groundBox3.setAsBox(3,100);
        // Create a fixture from our polygon shape and add it to our ground body
        groundBody3.createFixture(groundBox3, 0);
        // Clean up after ourselves
        groundBox3.dispose();

		BodyDef groundBodyDef4 =new BodyDef();
		// Set its world position3
		groundBodyDef4.position.set(new Vector2(50, 10));
		// Create a body from the defintion and add it to the world
		Body groundBody4 = boxWorld.createBody(groundBodyDef4);
		// Create a polygon shape
		PolygonShape groundBox4 = new PolygonShape();
		// (setAsBox takes half-width and half-height as arguments)
		groundBox4.setAsBox(0.5f,6);
		// Create a fixture from our polygon shape and add it to our ground body
		groundBody4.createFixture(groundBox4, 0);
		// Clean up after ourselves
		groundBox4.dispose();


	}



	@Override
	public void render () {


//BoxObjectManager.BOX_TO_WORLD = 100f
	//	debugMatrix=new Matrix4(b2dcam.combined);
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
		camera.viewportWidth = 100f;
		camera.viewportHeight = 100f * height/width;
		camera.update();
	}
}
