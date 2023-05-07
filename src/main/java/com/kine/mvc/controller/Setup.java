package com.kine.mvc.controller;

import java.util.stream.Collectors;

import com.kine.mvc.controller.services.CardService;
import com.kine.mvc.controller.services.PlayerService;
import com.kine.mvc.model.cards.Cards;
import com.kine.mvc.model.player.Player;

public class Setup implements Round {

    private Player first;
    private String allCards;

    public Setup(PlayerService ps, CardService cs) {
        this.first = ps.firstPlayer();
        cs.setupHands(ps.players());
        allCards = ps.players().stream()
                .map(t -> String.format("%s plays face up:\n%s",
                        Player.show(t),
                        Cards.show(t.getHand().faceUp().cards())))
                .collect(Collectors.joining("\n"));
    }

    @Override
    public boolean won() {
        return false;
    }

    @Override
    public Player player() {
        return first;
    }

    @Override
    public String info() {
        return String.format("== Players chose their face up cards! ==\n%s", allCards);
    }

    @Override
    public Play discard() {
        return () -> null;
    }

    @Override
    public Round previous() {
        return null;
    }

}
