package com.kine.mvc.controller;

import java.io.InputStream;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.kine.mvc.controller.services.CardServiceImpl;
import com.kine.mvc.model.Deck;
import com.kine.mvc.model.cards.*;
import com.kine.mvc.model.player.Player;

public class TestCardService {

    @Test
    void testCardService() {
        Inputs<String> inputs = () -> new String[] {
                "7", "a", "b", "-3", "2", "1"
        };

        testSetupHands(inputs.stream());
    }

    void testSetupHands(InputStream in) {
        System.setIn(in);
        var cards = Arrays.asList(Suit.values()).stream().map(s -> new Card(s, Rank.Ace)).collect(Collectors.toList());
        var deck = new Deck(cards);
        var hand = new PlayerHand(deck, 1);
        System.out.println(hand.inHand().cards());
        System.out.println(hand.faceDown().cards());
        System.out.println(hand.faceUp().cards());

        var cs = new CardServiceImpl(1);
        cs.setupHands(Arrays.asList(new Player(0, hand)));
        System.out.println();
        System.out.println(hand.inHand().cards());
        System.out.println(hand.faceDown().cards());
        System.out.println(hand.faceUp().cards());
    }
}
