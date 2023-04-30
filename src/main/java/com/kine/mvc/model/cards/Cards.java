package com.kine.mvc.model.cards;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.kine.mvc.model.effects.Effect;


@FunctionalInterface

public interface Cards {

    List<Card> list();

    default Optional<Card> topCard() {
        if (list() == null)
            return Optional.empty();
        if (list().size() == 0)
            return Optional.empty();
        return Optional.of(list()).map(s -> s.get(s.size() - 1));
    }

    default Effect getEffect() {
        return topCard().map(Card::getEffect).orElse(Effect.NONE);
    }

    public static String show(Optional<Cards> cards) {
        return cards.map(Cards::list).map(Cards::show).orElse("No cards");
    }

    public static String show(Cards cards){
        return show(cards.list());
    }

    public static String show(List<Card> hand) {
        Function<Integer, String> indexed = (i) -> String.format("%d: %s", i, Card.show(hand.get(i)));
        return Stream.iterate(0, i -> i + 1)
                .limit(hand.size())
                .map(indexed)
                .collect(Collectors.joining("\n"));
    }
}