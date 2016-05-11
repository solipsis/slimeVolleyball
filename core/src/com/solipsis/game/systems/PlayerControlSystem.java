package com.solipsis.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.solipsis.game.SlimeVolleyball;
import com.solipsis.game.components.PhysicsBody;
import com.solipsis.game.components.PlayerControlled;
import com.solipsis.game.components.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dave on 5/1/16.
 */
@Wire
public class PlayerControlSystem extends EntityProcessingSystem implements InputProcessor, GestureDetector.GestureListener {

    protected ComponentMapper<Position> positionMapper;
    protected ComponentMapper<PhysicsBody> bodyMapper;
    protected ComponentMapper<PlayerControlled> playerControlledComponentMapper;

    TouchInfo player1Info = new TouchInfo(new Vector2(0,0),0,-1);
    TouchInfo player2Info = new TouchInfo(new Vector2(0,0),0,-1);

    Map<Entity, Boolean> tapMap = new HashMap<Entity, Boolean>();

    Map<Body, Vector2> eventList = new HashMap<Body, Vector2>();

    private World boxWorld = SlimeVolleyball.boxWorld;

    boolean test;
    boolean p1Move;
    boolean p2Move;
    float testX;
    float testY;
    float p2MoveX;
    float p2MoveY;

    public PlayerControlSystem() { super(Aspect.all(PlayerControlled.class, Position.class, PhysicsBody.class));
        System.out.println("added control system");
        Gdx.input.setInputProcessor(new GestureDetector(this));
        Gdx.input.setInputProcessor(this);
        test = false;
    }

    @Override
    public void inserted(Entity e) {
        tapMap.put(e, Boolean.FALSE);
    }

    @Override
    public void removed(Entity e) {
        tapMap.remove(e);
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

        if (playerControlledComponentMapper.get(e).getPlayer() == 1 && bodyMapper.get(e).getBody().getPosition().y > 7) {
            p1Move = false;
        }

        if (playerControlledComponentMapper.get(e).getPlayer() == 2 && bodyMapper.get(e).getBody().getPosition().y > 7) {
            p2Move = false;
        }

        if (p1Move && playerControlledComponentMapper.get(e).getPlayer() == 1 && bodyMapper.get(e).getBody().getPosition().y < 7) {
            PhysicsBody body = bodyMapper.get(e);
            //body.getBody().applyForceToCenter(500,500, true);
            body.getBody().applyLinearImpulse(testX, testY, body.getBody().getPosition().x, body.getBody().getPosition().y, true);

            int maxSlimeSpeed = 18;
            if (body.getBody().getLinearVelocity().x > maxSlimeSpeed) {
                body.getBody().setLinearVelocity(maxSlimeSpeed, body.getBody().getLinearVelocity().y);
            }
            if (body.getBody().getLinearVelocity().x < -maxSlimeSpeed) {
                body.getBody().setLinearVelocity(-maxSlimeSpeed, body.getBody().getLinearVelocity().y);
            }
            if (body.getBody().getLinearVelocity().y > maxSlimeSpeed) {
                body.getBody().setLinearVelocity( body.getBody().getLinearVelocity().x, maxSlimeSpeed);
            }
            if (body.getBody().getLinearVelocity().y < -maxSlimeSpeed) {
                body.getBody().setLinearVelocity( body.getBody().getLinearVelocity().x, -maxSlimeSpeed);
            }


            p1Move = false;
        }

        if (p2Move && playerControlledComponentMapper.get(e).getPlayer() == 2 && bodyMapper.get(e).getBody().getPosition().y < 7) {
            PhysicsBody body = bodyMapper.get(e);
            //body.getBody().applyForceToCenter(500,500, true);
            body.getBody().applyLinearImpulse(p2MoveX, p2MoveY, body.getBody().getPosition().x, body.getBody().getPosition().y, true);

            int maxSlimeSpeed = 18;
            if (body.getBody().getLinearVelocity().x > maxSlimeSpeed) {
                body.getBody().setLinearVelocity(maxSlimeSpeed, body.getBody().getLinearVelocity().y);
            }
            if (body.getBody().getLinearVelocity().x < -maxSlimeSpeed) {
                body.getBody().setLinearVelocity(-maxSlimeSpeed, body.getBody().getLinearVelocity().y);
            }
            if (body.getBody().getLinearVelocity().y > maxSlimeSpeed) {
                body.getBody().setLinearVelocity( body.getBody().getLinearVelocity().x, maxSlimeSpeed);
            }
            if (body.getBody().getLinearVelocity().y < -maxSlimeSpeed) {
                body.getBody().setLinearVelocity( body.getBody().getLinearVelocity().x, -maxSlimeSpeed);
            }


            p2Move = false;
        }

        if (position.x < 0) position.x = 0;
        if (position.x > 800-64) position.x = 800-64;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("Touch down");

        System.out.println("Width/2 : " + (SlimeVolleyball.camera.viewportWidth / 2));

        Vector3 unprojected = SlimeVolleyball.camera.unproject(new Vector3(screenX, screenY, 0));
        System.out.println("screenX: " + unprojected.x);
        // player 1
        if (unprojected.x < SlimeVolleyball.camera.viewportWidth / 2) {
            System.out.println("updated p1");
            player1Info = new TouchInfo(new Vector2(unprojected.x, unprojected.y), 1, pointer);
        } else {
            System.out.println("updated p2");
            player2Info = new TouchInfo(new Vector2(unprojected.x, unprojected.y), 2, pointer);
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println("Touch up");
        System.out.println("Pointer: " + pointer);
        Vector3 unprojected = SlimeVolleyball.camera.unproject(new Vector3(screenX, screenY, 0));
        if (player1Info.touchPointer == pointer) {
            testX = (unprojected.x - player1Info.position.x) * 1000;
            testY = (unprojected.y - player1Info.position.y) * 1000;
            p1Move = true;
            System.out.println("P1 touch up");
            player1Info = new TouchInfo(new Vector2(0,0),0,-1);

        }

        if (player2Info.touchPointer == pointer) {
            p2MoveX = (unprojected.x - player2Info.position.x) * 1000;
            p2MoveY = (unprojected.y - player2Info.position.y) * 1000;
            p2Move = true;
            System.out.println("P2 touch up");
            player2Info = new TouchInfo(new Vector2(0,0),0,-1);

        }


        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        test = true;
        System.out.println("Tap **********************************************************************************");
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
       // boxWorld.QueryAABB(12,);
        //eventList.put(null, )
    //    System.out.println("FLLLLIIINNNNGGGG");
   //     velocityX = velocityX * 5;
   //     velocityY = velocityY * 5;

   //     testX = velocityX > 3000 ? 3000 : velocityX;
    //    testY = velocityY > 3000 ? 3000 : velocityY;

   //     test = true;
    //    return true;
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
