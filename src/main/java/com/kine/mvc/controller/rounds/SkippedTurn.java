package com.kine.mvc.controller.rounds;

import com.kine.mvc.controller.Play;
import com.kine.mvc.controller.Round;
import com.kine.mvc.model.player.Player;

public final class SkippedTurn implements Round {
    private final Round prev;
    private final Player player;

    public SkippedTurn(Round prev, Player player) {
        this.prev = prev;
        this.player = player;
    }

    @Override
    public Round previous() {
        return prev;
    }

    @Override
    public Play discard() {
        return () -> null;
    }

    @Override
    public String info() {
        return String.format("%s skips their turn", player);
    }

    @Override
    public Player player() {
        return player;
    }

    @Override
    public boolean won() {
        return false;
    }
}