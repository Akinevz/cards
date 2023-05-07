package com.kine.mvc.controller.services;

import java.util.ArrayList;
import java.util.List;

import com.kine.mvc.controller.Round;
import com.kine.mvc.model.Decklike;
import com.kine.mvc.model.cards.Card;
import com.kine.mvc.model.hand.Hand;
import com.kine.mvc.model.player.Player;

public interface CardService {

    void setupHands(List<Player> players);

    default void topUp(Hand h, Decklike deck, int handSize) {
        var diff = handSize - h.inHand().size();
        if (diff > 0)
            h.pickup(deck.deal(diff));
    }

    default List<Card> discardPile(Round played) {
        var agg = new ArrayList<Card>();
        var last = played;
        while (last.cardsPlayed()) {
            agg.addAll(last.discard().played());
            last = last.previous();
        }
        return agg;
    }
}
