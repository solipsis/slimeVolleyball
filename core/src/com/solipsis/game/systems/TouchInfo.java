package com.solipsis.game.systems;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by dave on 5/10/2016.
 */
public class TouchInfo {

    Vector2 position;
    int player;
    int touchPointer;

    public TouchInfo(Vector2 position, int player,  int touchPointer) {
        this.player = player;
        this.position = position;
        this.touchPointer = touchPointer;
    }
}
