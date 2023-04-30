package com.kine.mvc.controller;

import com.kine.mvc.model.cards.Cards;
import com.kine.mvc.model.player.Player;

public class PickupTurn implements Turn {

    private Turn lastTurn;
    private Player player;
    private String description;

    public PickupTurn(Turn lastTurn, Player player, Cards pile) {
        player.pickup(pile);
        var description = String.format("%s picks up the play deck!", Player.show(player));
        this.lastTurn = lastTurn;
        this.player = player;
        this.description = description;
    }

    @Override
    public String info() {
        return description;
    }

    @Override
    public Turn previous() {
        return lastTurn;
    }

    @Override
    public Player player() {
        return player;
    }

@Override
public Cards cards() {
    return () -> null;
}
}
