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

    Map<Entity, Boolean> tapMap = new HashMap<Entity, Boolean>();

    Map<Body, Vector2> eventList = new HashMap<Body, Vector2>();

    private World boxWorld = SlimeVolleyball.boxWorld;

    boolean test;
    float testX;
    float testY;

    public PlayerControlSystem() { super(Aspect.all(PlayerControlled.class, Position.class, PhysicsBody.class));
        System.out.println("added control system");
        Gdx.input.setInputProcessor(new GestureDetector(this));
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

        if (test) {
            PhysicsBody body = bodyMapper.get(e);
            //body.getBody().applyForceToCenter(500,500, true);
            body.getBody().applyLinearImpulse(testX, testY, body.getBody().getPosition().x, body.getBody().getPosition().y, true);
           // if (body.getBody().getLinearVelocity().x > 3000) {
           //     body.getBody().setL
          //  }
            test = false;
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
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
        System.out.println("FLLLLIIINNNNGGGG");
        velocityX = velocityX * 5;
        velocityY = velocityY * 5;

        testX = velocityX > 3000 ? 3000 : velocityX;
        testY = velocityY > 3000 ? 3000 : velocityY;

        test = true;
        return true;

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
