package com.solipsis.game.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by dave on 5/1/16.
 */
public class Sprite extends Component {
    public com.badlogic.gdx.graphics.g2d.Sprite sprite;

    public Sprite() {
    }

    public void init(Texture texture) {
        sprite = new com.badlogic.gdx.graphics.g2d.Sprite();
        sprite.setTexture(texture);

         }
}
