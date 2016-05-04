package com.solipsis.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector2;
import com.solipsis.game.components.Box2dPhysics;
import com.solipsis.game.components.PlayerControlled;
import com.solipsis.game.components.Position;

/**
 * Created by dave on 5/4/16.
 */
@Wire
public class PhysicsSystem extends EntityProcessingSystem {

    ComponentMapper<Position> pm;
    ComponentMapper<Box2dPhysics> bm;

    public PhysicsSystem() { super(Aspect.all(Position.class, Box2dPhysics.class));
        System.out.println("added physics system");
    }

    @Override
    protected void process(Entity e) {
        Position p = pm.get(e.getId());
        Box2dPhysics body = bm.get(e.getId());
        p.x = body.getBody().getPosition().x;
        p.y = body.getBody().getPosition().y;



    }
}
