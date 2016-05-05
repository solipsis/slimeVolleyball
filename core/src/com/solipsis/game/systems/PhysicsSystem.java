package com.solipsis.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.solipsis.game.components.PhysicsBody;
import com.solipsis.game.components.Position;

/**
 * Created by dave on 5/4/16.
 */
@Wire
public class PhysicsSystem extends EntityProcessingSystem {

    ComponentMapper<Position> pm;
    ComponentMapper<PhysicsBody> bm;

    public PhysicsSystem() { super(Aspect.all(Position.class, PhysicsBody.class));
        System.out.println("added physics system");
    }

    @Override
    protected void process(Entity e) {
        Position p = pm.get(e.getId());
        PhysicsBody body = bm.get(e.getId());
        p.x = body.getBody().getPosition().x;
        p.y = body.getBody().getPosition().y;


      //  System.out.println("p.x = " + p.x + " -- body.x = " + body.getBody().getPosition().x);
    }
}
