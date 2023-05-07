package com.kine.mvc.controller.rounds;

import com.kine.mvc.controller.Play;
import com.kine.mvc.controller.Round;
import com.kine.mvc.model.player.Player;

public class BlockedRound extends RegularRound {

    public BlockedRound(Player player, Play play, Round last) {
        super(player, play, last);
    }

    @Override
    public String info() {
        return String.format("%s played a blocker! Next player skips a turn", Player.show(player()));
    }

}
