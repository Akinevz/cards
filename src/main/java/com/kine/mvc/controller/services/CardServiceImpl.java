package com.kine.mvc.controller.services;

import java.util.List;

import com.kine.mvc.model.cards.Card;
import com.kine.mvc.model.cards.Cards;
import com.kine.mvc.model.player.Player;
import com.kine.mvc.view.Input;
import com.kine.mvc.view.Parser;

public final class CardServiceImpl implements CardService {
    private final int handSize;

    public CardServiceImpl(int handSize) {
        this.handSize = handSize;
    }

    @Override
    public void setupHands(List<Player> players) {
        for (Player player : players) {
            var hand = player.getHand().inHand();
            Parser<Card> cardParser = Input.intParser().andThen(hand::play);
            var cards = Input.<Card>readAggregate(cardParser, set -> String.format("%s, choose card #%d to play face up:\n%s\n",
                    Player.show(player), set.size() + 1,
                    Cards.show(hand)), handSize);

            player.getHand().faceUp().cards().addAll(cards.get());
        }
    }

}