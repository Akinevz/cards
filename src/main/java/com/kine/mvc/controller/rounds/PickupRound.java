package com.kine.mvc.controller.rounds;

import java.util.List;

import com.kine.mvc.controller.Play;
import com.kine.mvc.controller.Round;
import com.kine.mvc.model.cards.Card;
import com.kine.mvc.model.player.Player;

public class PickupRound implements Round {
    private final Player player;
    private final String infoStr;
    private Round previous;

    public PickupRound(Player player, List<Card> discardPile, Round previous) {
        this.player = player;
        this.previous = previous;
        this.infoStr = String.format("%s picks up the cards!", Player.show(player));
        player.getHand().pickup(discardPile);
    }

    @Override
    public Player player() {
        return player;
    }

    @Override
    public boolean won() {
        return false;
    }

    @Override
    public String info() {
        return infoStr;
    }

    @Override
    public Play discard() {
        return () -> null;
    }

    @Override
    public Round previous() {
        return previous;
    }
}