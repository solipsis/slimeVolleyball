package com.solipsis.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.solipsis.game.SlimeVolleyball;
import com.solipsis.game.components.Ball;
import com.solipsis.game.components.PhysicsBody;
import com.solipsis.game.components.PlayerControlled;
import com.solipsis.game.components.Position;

/**
 * Created by dave on 5/10/2016.
 */
@Wire
public class BallSystem extends EntityProcessingSystem {

    protected ComponentMapper<PhysicsBody> bm;
    protected ComponentMapper<Position> pm;

    int floor = 6;


    public BallSystem() { super(Aspect.all(PhysicsBody.class, Position.class, PhysicsBody.class, Ball.class));
        System.out.println("added ball system");
    }

    @Override
    protected void process(Entity e) {

        Body pbody = bm.get(e).getBody();
        if (pbody.getLinearVelocity().y > 35) {
            pbody.setLinearVelocity(pbody.getLinearVelocity().x, 35);
        }

        if (pm.get(e).y < floor) {
            Body body = bm.get(e).getBody();
            body.setTransform(new Vector2(50, 50), 0);
            body.applyLinearImpulse(new Vector2((int)(Math.random()*100)-50, (int)(Math.random() * 100) + 30), new Vector2(body.getPosition().x, body.getPosition().y), true);
            body.setLinearVelocity((int)(Math.random()*30)-15, (int)(Math.random()*30) - 15);

            //if (body.getLinearVelocity().x > 500) {
           //     body.setLinearVelocity(10, 10);
           // }
            System.out.println("reset ball position");
        }
    }
}
