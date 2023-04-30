package com.kine.mvc.controller;

import com.kine.mvc.model.cards.Cards;
import com.kine.mvc.model.player.Player;

public class SkippedTurn implements Turn {

    private String description;
    private Player player;
    private Turn lastTurn;

    public SkippedTurn(Turn lastTurn, Player skips, Player goes) {
        var description = String.format("%s skips a turn, %s goes next!", Player.show(skips), Player.show(goes));
        this.lastTurn = lastTurn;
        this.player = skips;
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
