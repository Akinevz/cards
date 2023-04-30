package com.kine.mvc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.kine.mvc.model.cards.*;
import com.kine.mvc.model.effects.Effect;
import com.kine.mvc.model.player.Player;

public class Table {

    private Deck deck;

    private List<Player> players;

    private Turn lastTurn;

    public Cards discardDeck() {
        class DiscardDeck {

            public static List<Card> count(Turn completeTurn) {
                if (completeTurn.previous() == null || completeTurn.effect().equals(Effect.CLEAR)) {
                    return new ArrayList<>();
                }
                var prev = count(completeTurn.previous());
                prev.addAll(completeTurn.cards().list());
                return prev;
            }

        }
        return () -> DiscardDeck.count(lastTurn);
    }

    private Player nextPlayer(Player lastPlayer) {
        var index = lastPlayer.index();
        var pNext = players.get(index + 1 % players.size());
        return pNext;
    }

    Table(Deck cards, int playerCount, int handSize) {
        this.deck = cards;
        this.players = Stream.iterate(0, (i) -> i + 1)
                .map((i) -> new Player(deck, handSize, i))
                .limit(playerCount)
                .collect(Collectors.toList());
        this.lastTurn = new Setup(players, handSize);
        
    }

    Turn next() {
        System.out.println(Turn.show(lastTurn));
        var lastPlayer = lastTurn.player();
        var player = nextPlayer(lastPlayer);

        Turn turn = Turn.forPlayer(player, this);
        this.lastTurn = turn;
        return turn;
    }

    public boolean beats(Card c) {
        var last = lastTurn.topCard();
        var beats = last.map(c::beats);
        return beats.orElse(true);
    }

    public Deck getDeck() {
        return deck;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Turn getLastTurn() {
        return lastTurn;
    }

}