package com.solipsis.game.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.solipsis.game.SlimeVolleyball;

import java.util.Arrays;

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


        PolygonShape polygonShape = new PolygonShape();
        Vector2[] vertices = new Vector2[8];
        int radius = 10;
        vertices[0] =  new Vector2(0,0);
        for (int i = 0; i < 7; i++) {
            float angle = i / 6.0f * 180 * SlimeVolleyball.DEGTORAD;
            System.out.println(angle);
            vertices[i+1] = new Vector2((float)(radius * Math.cos(angle)), (float)(radius * Math.sin(angle)));
        }


        System.out.println(Arrays.toString(vertices));
        //polygonShape.setAsBox(10, 10);

        polygonShape.set(vertices);


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
        circleShape.setRadius(1);

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
