package com.kine.mvc.controller.rounds;

import com.kine.mvc.controller.Play;
import com.kine.mvc.controller.Round;
import com.kine.mvc.model.player.Player;

public class ClearRound extends RegularRound {

    public ClearRound(Player player, Play play, Round prev) {
        super(player, play, prev);
    }

    @Override
    public String info() {
        return String.format("%s cleared the deck!", Player.show(player()));
    }

    @Override
    public Play discard() {
        return () -> null;
    }

}
