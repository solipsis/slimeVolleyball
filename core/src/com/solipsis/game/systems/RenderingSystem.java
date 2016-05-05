package com.solipsis.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.solipsis.game.components.Position;
import com.solipsis.game.components.Sprite;
import com.solipsis.game.components.SpriteReference;
import com.solipsis.game.managers.AssetManager;

@Wire
public class RenderingSystem extends EntityProcessingSystem {

    protected ComponentMapper<SpriteReference> spriteReferenceMapper;
    protected ComponentMapper<Sprite> spriteMapper;
    protected ComponentMapper<Position> positionMapper;

    OrthographicCamera camera;

    private SpriteBatch batch;
    private AssetManager assetManager = new AssetManager();

    public RenderingSystem() {
        super(Aspect.all(SpriteReference.class, Position.class, Sprite.class));

        batch = new SpriteBatch();
    }


    @Override
    public void inserted(Entity entity) {
        Sprite sprite = spriteMapper.get(entity.getId());
        SpriteReference spriteReference = spriteReferenceMapper.get(entity.getId());
        sprite.init(assetManager.get(spriteReference.spriteFile));
    }


    @Override
    protected void begin() {
        camera = new OrthographicCamera(1000,1000);
    //    camera.update();

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
    }

    @Override
    protected void process(Entity e) {
        batch.setProjectionMatrix(camera.combined);
        Sprite sprite = spriteMapper.get(e);
        SpriteReference spriteReference = spriteReferenceMapper.get(e.getId());
        sprite.init(assetManager.get(spriteReference.spriteFile));
        Position position = positionMapper.get(e);

        batch.draw(sprite.sprite.getTexture(), position.x - (sprite.sprite.getTexture().getWidth()/2), position.y - (sprite.sprite.getTexture().getWidth()/2));

    }

    @Override
    protected void end() {
        batch.end();
    }
}
