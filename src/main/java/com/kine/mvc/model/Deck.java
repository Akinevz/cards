package com.kine.mvc.model;

import java.util.*;

import com.kine.mvc.model.cards.Card;

public class Deck implements Decklike {
    private final Random rand = new Random(1);
    private LinkedList<Card> cards;

    public Deck(Collection<Card> c) {
        this.cards = new LinkedList<>(c);
    }

    public Deck() {
        this.cards = new LinkedList<>();
    }

    public void shuffle() {
        Collections.shuffle(cards, rand);
    }

    @Override
    public LinkedList<Card> cards() {
        return cards;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}