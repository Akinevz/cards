package com.kine.mvc.model.cards;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck implements Decklike{
    private final Random rand = new Random(1);
    private Queue<Card> remaining;
    private List<Card> cards;

    public Deck(List<Card> c) {
        this.cards = new ArrayList<>(c);
    }

    public Deck() {
        this.cards = new ArrayList<>();
    }

    public void shuffle() {
        Collections.shuffle(cards, rand);
    }

    public Queue<Card> remaining() {
        if (remaining == null)
            remaining = new ArrayDeque<>(cards);
        return remaining;
    }

    public List<Card> deal(int count) {
        var queue = remaining();
        Supplier<Card> pop = () -> {
            try {
                return queue.remove();
            } catch (NoSuchElementException ex) {
                return null;
            }
        };
        return Stream.generate(pop)
                .limit(count)
                .collect(Collectors.toList());
    }
}