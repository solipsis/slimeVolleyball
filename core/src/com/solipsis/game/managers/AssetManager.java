package com.solipsis.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by dave on 5/1/16.
 */
public class AssetManager {
    public Texture slime;
    public Texture ball;

    public enum SpriteFile {
        SLIME,
        BALL
    }

    public AssetManager() {
        slime = new Texture(Gdx.files.internal("slimeLeft.png"));
    }

    public Texture get(SpriteFile sprite) {
        if (sprite.equals(SpriteFile.SLIME)) {
            return slime;
        }

        return null;
    }




}
