package com.kine.mvc.controller;

import java.util.Optional;

import com.kine.mvc.model.cards.Card;
import com.kine.mvc.model.cards.Cards;
import com.kine.mvc.model.player.Player;

public class LowerThanTurn implements Turn {

    public LowerThanTurn(Player player, Optional<Card> topCard) {
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
