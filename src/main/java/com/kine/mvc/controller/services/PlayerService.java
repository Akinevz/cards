package com.kine.mvc.controller.services;

import java.util.*;
import java.util.function.Function;

import com.kine.mvc.controller.Play;
import com.kine.mvc.controller.Table;
import com.kine.mvc.model.Deck;
import com.kine.mvc.model.cards.Card;
import com.kine.mvc.model.cards.Cards;
import com.kine.mvc.model.hand.Hand;
import com.kine.mvc.model.player.Player;
import com.kine.mvc.view.Input;
import com.kine.mvc.view.Show;

@FunctionalInterface
public interface PlayerService {

    List<Player> players();

    default Player firstPlayer() {
        Function<Hand, Card> smallestHand = (hand) -> {
            return hand.inHand().cards().stream().sorted(Card.comparator).findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Player has an empty hand"));
        };
        Comparator<Player> byHand = (p1, p2) -> {
            var h1 = p1.getHand();
            var h2 = p2.getHand();
            var c1 = smallestHand.apply(h1);
            var c2 = smallestHand.apply(h2);
            return Card.comparator.compare(c1, c2);
        };
        return players().stream().sorted(byHand).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Game has no players"));
    }

    default Player nextPlayer(Table table, Player p) {
        return table.players().get((p.index() + 1) % table.players().size());
    }

    default Play createPlay(Player player) {
        var handSize = player.getHand().inHand().size();
        var proposed = Input.<Card>readMulti(player.getHand().inHand().cards(),
                s -> Input.intParser().parse(s).map(player.getHand().inHand()::play),
                set -> String.format("\n%s, select cards for play (%d/%d):\n%s", Player.show(player), set.size(),
                        handSize, Show.forT(set)));

        return () -> new ArrayList<>(proposed);
    }

    default List<Card> createPickup(Deck deck, int dealSize) {
        return deck.deal(dealSize);
    }

    default List<Card> createPickup(Cards tableCards, int size) {
        var ret = new ArrayList<Card>();
        for (int i = 0; i < size; i++) {
            ret.add(tableCards.play(0));
        }
        return ret;
    }

    default List<Card> createPickup(Hand hand) {
        if (hand.isWon()) {
            return Arrays.asList();
        }
        if (hand.faceUp().cards().isEmpty()) {
            return createPickup(hand.faceDown(), 1);
        }
        return createPickup(hand.faceUp(), hand.faceUp().size());
    }

    default List<Card> createPickup(boolean deckEmpty, Hand hand, Deck deck, int missing) {
        if (deckEmpty) {
            var cards = createPickup(hand);
            return cards;
        }
        var cards = createPickup(deck, missing);
        return cards;
    }

    default boolean isPlayerMissingCards(Hand hand, int handSize) {
        return hand.inHand().size() < handSize;
    }

    default int missing(Hand hand, int handSize) {
        return handSize - hand.inHand().size();
    }

}
