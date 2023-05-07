package com.kine.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import com.kine.mvc.model.Deck;
import com.kine.mvc.model.cards.*;
import com.kine.mvc.view.Input;

public class App {

    private static final String HOW_MANY_STARTING_CARDS_DOES_EACH_PLAYER_GET = "How many starting cards does each player get?: ";
    private static final String HOW_MANY_PLAYERS_ARE_PLAYING = "How many players are playing?: ";

    public static void main(String[] args) throws Exception {
        List<Card> cards = new ArrayList<>(52);
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        Deck deck = new Deck(cards);
        deck.shuffle();

        int playerCount = Input.readParsed(Input.intParser(), HOW_MANY_PLAYERS_ARE_PLAYING);
        int handSize = Input.readParsed(Input.intParser(), HOW_MANY_STARTING_CARDS_DOES_EACH_PLAYER_GET);
        Table table = new Table(playerCount, handSize, deck);
        Round round;
        while (!(round = table.playRound()).won()) {
            System.out.println(Round.show(round));
        }
    }
}
