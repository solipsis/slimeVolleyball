package com.solipsis.game.components;

import com.artemis.Component;

/**
 * Created by dave on 5/1/16.
 */
public class PlayerControlled extends Component {

        int player;

        public PlayerControlled(int player) {
            this.player = player;
        }

    public int getPlayer() {
        return player;
    }
}
