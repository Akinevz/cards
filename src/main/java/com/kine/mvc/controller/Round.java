
package com.kine.mvc.controller;

import java.util.Objects;
import java.util.Optional;

import com.kine.mvc.model.cards.Card;
import com.kine.mvc.model.player.Player;

public interface Round {

    static String show(Round r) {
        return String.format("%s", r.info());
    }

    Round previous();

    Play discard();

    String info();

    Player player();

    boolean won();

    default boolean cardsPlayed() {
        return Objects.nonNull(discard().played());
    }

    default Optional<Card> topCard() {
        return discard().topCard();
    }

}
