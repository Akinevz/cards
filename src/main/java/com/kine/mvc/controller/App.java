package com.kine.mvc.controller;

import java.util.ArrayList;

import com.kine.mvc.model.cards.Card;
import com.kine.mvc.model.cards.Deck;
import com.kine.mvc.view.Input;


public class App {

    private static final String HOW_MANY_STARTING_CARDS_DOES_EACH_PLAYER_GET = "How many starting cards does each player get?: ";
    private static final String HOW_MANY_PLAYERS_ARE_PLAYING = "How many players are playing?: ";

    public static void main(String[] args) throws Exception {
        ArrayList<Card> cards = new ArrayList<>(52);

        Deck deck = new Deck(cards);
        deck.shuffle();

        int playerCount = Input.readParsed(Input.intParser, HOW_MANY_PLAYERS_ARE_PLAYING);
        int handSize = Input.readParsed(Input.intParser, HOW_MANY_STARTING_CARDS_DOES_EACH_PLAYER_GET);

        Table arrangement = new Table(deck, playerCount, handSize);

        Turn round;
        do {
            round = arrangement.next();
        } while (!round.player().won());
        System.out.println(Turn.show(round));
    }
}
