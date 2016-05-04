package com.solipsis.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.solipsis.game.components.PlayerControlled;
import com.solipsis.game.components.Position;

/**
 * Created by dave on 5/1/16.
 */
@Wire
public class PlayerControlSystem extends EntityProcessingSystem {

    protected ComponentMapper<Position> positionMapper;

    public PlayerControlSystem() { super(Aspect.all(PlayerControlled.class, Position.class));
        System.out.println("added control system");
    }

    @Override
    protected void process(Entity e) {
        Position position = positionMapper.get(e);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= 200 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += 200 * Gdx.graphics.getDeltaTime();
        }

        if (position.x < 0) position.x = 0;
        if (position.x > 800-64) position.x = 800-64;
    }
}
