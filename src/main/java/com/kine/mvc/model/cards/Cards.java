package com.kine.mvc.model.cards;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.kine.mvc.model.effects.Effect;


@FunctionalInterface

public interface Cards {

    List<Card> cards();

    default Card play(int index){
        return cards().remove(index);
    }

    default Optional<Card> topCard() {
        if (cards() == null)
            return Optional.empty();
        if (cards().size() == 0)
            return Optional.empty();
        return Optional.of(cards()).map(s -> s.get(s.size() - 1));
    }

    default Effect getEffect() {
        return topCard().map(Card::getEffect).orElse(Effect.NONE);
    }

    public static String show(Cards cards){
        return show(cards.cards());
    }

    public static String show(List<Card> hand) {
        Function<Integer, String> indexed = (i) -> String.format("%d: %s", i, Card.show(hand.get(i)));
        return Stream.iterate(0, i -> i + 1)
                .limit(hand.size())
                .map(indexed)
                .collect(Collectors.joining("\n"));
    }

    default int size() {return cards().size();}

    default Stream<Card> stream(){
        return cards().stream();
    }
}