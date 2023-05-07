package com.kine.mvc.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.kine.mvc.controller.rounds.BlockedRound;
import com.kine.mvc.controller.rounds.PickupRound;
import com.kine.mvc.controller.services.*;
import com.kine.mvc.model.Deck;
import com.kine.mvc.model.cards.Card;
import com.kine.mvc.model.player.Player;

public class Table {

    private int handSize;
    List<Player> players;

    public List<Player> players() {
        return players;
    }

    private Deck deck;
    private PlayerService playerService;
    private CardService cardService;

    public Table(int playerCount, int handSize, Deck deck) {
        this.deck = deck;
        this.handSize = handSize;
        this.players = Stream.iterate(0, i -> i + 1)
                .limit(playerCount)
                .map(i -> new Player(i, new PlayerHand(deck, handSize)))
                .collect(Collectors.toList());
        this.playerService = () -> players;
        this.cardService = new CardServiceImpl(handSize);
        this.lastRound = new Setup(playerService, cardService);
    }

    public Deck getRemainingDeck() {
        return deck;
    }

    private Round lastRound;

    public Round playRound() {
        Player player;
        if (lastRound instanceof PickupRound || lastRound instanceof Setup) {
            player = lastRound.player();
        } else {
            player = playerService.nextPlayer(this, lastRound.player());
        }
        if (lastRound instanceof BlockedRound br) {
            var ret = Play.skip(player, lastRound);
            lastRound = ret;
            return ret;
        }
        var play = playerService.createPlay(player);
        var hand = player.getHand();
        if (play.isEmpty()) {
            List<Card> discardPile = cardService.discardPile(lastRound);
            var ret = play.pickup(player, discardPile, lastRound);
            lastRound = ret;
            return playRound();
        }
        if (playerService.isPlayerMissingCards(hand, handSize)) {
            var missing = playerService.missing(hand, handSize);
            var topUp = playerService.createPickup(deck.isEmpty(), hand, deck, missing);
            hand.pickup(topUp);
        }

        Round ret;
        switch (play.effect()) {
            case BLOCK:
                ret = play.block(player, lastRound);
                break;
            case CLEAR:
                ret = play.clear(player, lastRound);
                break;
            default:
                ret = play.round(player, lastRound);
                break;
        }
        lastRound = ret;
        return ret;
    }

}
