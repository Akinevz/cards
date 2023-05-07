package com.kine.mvc.controller;

import java.util.*;

import com.kine.mvc.model.Decklike;
import com.kine.mvc.model.cards.Card;
import com.kine.mvc.model.cards.Cards;
import com.kine.mvc.model.hand.CardIndexes;
import com.kine.mvc.model.hand.Hand;

public final class PlayerHand implements Hand {
    List<Card> hand = new ArrayList<>();
    List<Card> faceDown = new ArrayList<>();
    List<Card> faceUp = new ArrayList<>();

    public PlayerHand(Decklike deck, int handSize) {
        faceDown.addAll(deck.deal(handSize));
        hand.addAll(deck.deal(handSize * 2));
    }

    @Override
    public Cards inHand() {
        return () -> hand;
    }

    @Override
    public Cards faceDown() {
        return () -> faceDown;
    }

    @Override
    public Cards faceUp() {
        return () -> faceUp;
    }

    @Override
    public int pickup(List<Card> pile) {
        var i = 0;
        for (Card card : pile) {
            hand.add(card);
            i++;
        }
        return i;
    }

    @Override
    public CardIndexes inHand(Set<Card> cards) {
        var indexes = new ArrayList<Integer>();
        for (Card c : cards) {
            var index = hand.indexOf(c);
            if (index >= 0)
                indexes.add(index);
        }
        return () -> indexes;
    }

    @Override
    public List<Card> play(CardIndexes indexes) {
        var cards = new ArrayList<Card>();
        for (int index : indexes.indexes()) {
            cards.add(play(index));
        }
        return cards;
    }

    @Override
    public Card play(int index) {
        return hand.remove(index);
    }

    @Override
    public boolean isWon() {
        return false;
    }

}