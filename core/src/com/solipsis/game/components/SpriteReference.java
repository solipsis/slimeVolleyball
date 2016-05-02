package com.solipsis.game.components;

import com.artemis.Component;
import com.solipsis.game.managers.AssetManager;

/**
 * Created by dave on 5/1/16.
 */
public class SpriteReference extends Component {
    public AssetManager.SpriteFile spriteFile;

    public SpriteReference(AssetManager.SpriteFile spriteFile) {
        this.spriteFile = spriteFile;
    }
}
