package com.kine.mvc.controller;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.kine.mvc.model.cards.Card;
import com.kine.mvc.model.cards.Cards;
import com.kine.mvc.model.hand.Hand;
import com.kine.mvc.model.player.Player;

public class Setup implements Turn {

    private String playersInfo;
    private Player firstPlayer;

    @Override
    public String info() {
        return playersInfo;
    }

    public Setup(List<Player> players, int handSize) {
        // let every player determine their face up hand
        players.forEach(p -> p.getStrategy().determineFaceUp(handSize, p.getHand()));
        Function<Player, String> playerInfo = (player) -> String.format("%s puts down %s",
                Player.show(player),
                Cards.show(player.getHand().faceUp()));
        // generate descriptor for the play
        this.playersInfo = players.stream().map(playerInfo).collect(Collectors.joining("\n"));
        this.firstPlayer = firstPlayer(players);
    }

    @Override
    public Player player() {
        return firstPlayer;
    }

    Player firstPlayer(List<Player> players) throws NoSuchElementException {
        Function<Player, Card> smallestCard = (player) -> {
            return Stream.of(player)
                    .map(Player::getHand)
                    .map(Hand::cards)
                    .flatMap(List::stream)
                    .sorted(Card::beats)
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Player has no cards"));
        };
        Comparator<? super Player> least = new Comparator<>() {

            @Override
            public int compare(Player o1, Player o2) {
                var c1 = smallestCard.apply(o1);
                var c2 = smallestCard.apply(o2);
                return Card.beats(c1, c2);
            }

        };
        var playerWithLowestCard = players.stream()
                .sorted(least)
                .findFirst().orElseThrow(() -> new NoSuchElementException("Game has no players"));
        return playerWithLowestCard;
    }

    @Override
    public Turn previous() {
        return null;
    }

    @Override
    public Cards cards() {
        return () -> null;
    }

}
