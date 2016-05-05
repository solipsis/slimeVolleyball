package com.solipsis.game.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by dave on 5/4/16.
 */
public class PhysicsBody extends Component {
    Body body;

    public PhysicsBody(World world, Vector2 position) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);
        body = world.createBody(bodyDef);
      //  body.setTransform(position, 0);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(128, 128);



        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 1;
        fixtureDef.restitution = 0.9f;
        fixtureDef.friction = 0.5f;

        Fixture fixture = body.createFixture(fixtureDef);


    }

    public PhysicsBody(World world, Vector2 position, String s) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.add(position);
        body = world.createBody(bodyDef);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(20);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 1;
        fixtureDef.restitution = 0.8f;
        Fixture fixture = body.createFixture(fixtureDef);


    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
