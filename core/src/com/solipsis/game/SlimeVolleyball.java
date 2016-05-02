package com.solipsis.game;

import com.artemis.World;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.solipsis.game.components.PlayerControlled;
import com.solipsis.game.components.Position;
import com.solipsis.game.components.Sprite;
import com.solipsis.game.components.SpriteReference;
import com.solipsis.game.managers.AssetManager;

public class SlimeVolleyball extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	World world;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		world = new World();
		int slime = world.create();
		world.edit(slime)
				.add(new Position())
				.add(new PlayerControlled())
				.add(new Sprite())
				.add(new SpriteReference(AssetManager.SpriteFile.SLIME));
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
		world.process();
	}
}
