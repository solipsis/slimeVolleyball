package com.solipsis.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.solipsis.game.SlimeVolleyball;
import com.solipsis.game.components.PhysicsBody;
import com.solipsis.game.components.PlayerControlled;
import com.solipsis.game.components.Position;
import com.solipsis.game.components.Slime;

/**
 * Created by dave on 5/11/2016.
 */
@Wire
public class SlimeSystem extends EntityProcessingSystem {

    ComponentMapper<PlayerControlled> playerMapper;
    ComponentMapper<PhysicsBody> bodyMapper;

    @Override
    protected void process(Entity e) {
        if (playerMapper.get(e).getPlayer() == 1 && bodyMapper.get(e).getBody().getPosition().x > 42) {
            bodyMapper.get(e).getBody().setTransform(42, bodyMapper.get(e).getBody().getPosition().y, 0);
        }
        if (playerMapper.get(e).getPlayer() == 2 && bodyMapper.get(e).getBody().getPosition().x < 58) {
            bodyMapper.get(e).getBody().setTransform(58, bodyMapper.get(e).getBody().getPosition().y, 0);
        }
    }



    public SlimeSystem() { super(Aspect.all(PhysicsBody.class, Position.class, Slime.class, PlayerControlled.class));
        System.out.println("Added slime system");
    }
}
