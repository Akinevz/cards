package com.kine.mvc.controller;

import com.kine.mvc.model.cards.Cards;
import com.kine.mvc.model.player.Player;

public class NonTurn implements Turn {

    public NonTurn(Player player) {
    }

    @Override
    public String info() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'info'");
    }

    @Override
    public Turn previous() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'previous'");
    }

    @Override
    public Player player() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'player'");
    }

    @Override
    public Cards cards() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cards'");
    }

}
