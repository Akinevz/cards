package com.kine.mvc.model.player;

import com.kine.mvc.model.hand.Hand;

public class Player {

    private final int playerIndex;
    private final Hand hand;

    public static String show(Player p) {
        return p.name();
    }

    public Player(int index, Hand playerHand) {
        this.hand = playerHand;
        this.playerIndex = index;
    }

    public int index() {
        return playerIndex;
    }

    public String name() {
        return String.format("Player #%d", playerIndex+1);
    }

    public boolean won() {
        return hand.isWon();
    }

    public Hand getHand() {
        return hand;
    }

}
