package com.kine.mvc.controller.rounds;

import com.kine.mvc.controller.Play;
import com.kine.mvc.controller.Round;
import com.kine.mvc.model.cards.Cards;
import com.kine.mvc.model.player.Player;

public class RegularRound implements Round {
    private final Player player;
    private final Round prev;
    private Play play;

    public RegularRound(Player player, Round prev) {
        this.player = player;
        this.prev = prev;
    }

    public RegularRound(Player player, Play play, Round prev) {
        this.player = player;
        this.play = play;
        this.prev = prev;
    }

    @Override
    public Round previous() {
        return prev;
    }

    @Override
    public Play discard() {
        return play;
    }

    @Override
    public String info() {
        return String.format("%s played:\n%s", Player.show(player), Cards.show(play.played()));
    }

    @Override
    public Player player() {
        return player;
    }

    @Override
    public boolean won() {
        return player.won();
    }
}