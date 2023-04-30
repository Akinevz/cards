package com.kine.mvc.model.player;

import com.kine.mvc.model.hand.Hand;

public class Player {

    private final int playerIndex;
    private final Hand hand;
    private final Strategy strategy;

    public static String show(Player p) {
        return String.format("Player #%d", p.playerIndex);
    }

    public Player(int playerIndex, Strategy strategy, Hand hand) {
        this.hand = hand;
        this.playerIndex = playerIndex;
        this.strategy = strategy;
    }

    public int index() {
        return playerIndex;
    }

    public String name() {
        return show(this);
    }

    public boolean won() {
        return hand.isWon();
    }

    public Hand getHand() {
        return hand;
    }

    public Strategy getStrategy() {
        return strategy;
    }

}
