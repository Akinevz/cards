package com.kine.mvc.controller;

import java.util.*;

import com.kine.mvc.controller.rounds.*;
import com.kine.mvc.model.cards.Card;
import com.kine.mvc.model.effects.Effect;
import com.kine.mvc.model.player.Player;

@FunctionalInterface
public interface Play {
    List<Card> played();

    default int size() {
        return (Objects.nonNull(played())) ? played().size() : 0;
    }

    default boolean beats(Play prev) {
        var prevCard = prev.topCard();
        return topCard().flatMap(o -> prevCard.map(o::beats)).orElse(true);
    }

    default Round pickup(Player player, List<Card> discardPile, Round previous) {
        return new PickupRound(player, discardPile, previous);
    }

    default boolean beats(Round toBeat) {
        return topCard().flatMap(c -> toBeat.topCard().map(c::beats)).orElse(!isEmpty());
    }

    default Round block(Player player, Round prev) {
        if (this.beats(prev))
            return new BlockedRound(player, this, prev);
        return pickup(player, played(), prev);
    }

    default Round clear(Player player, Round prev) {
        if (this.beats(prev))
            return new ClearRound(player, this, prev);
        return pickup(player, played(), prev);
    }

    default Round round(Player player, Round prev) {
        if (this.beats(prev)) {
            return new RegularRound(player, this, prev);
        }
        return pickup(player, played(), prev);
    }

    default boolean isEmpty() {
        return size() == 0;
    }

    default Optional<Card> topCard() {
        if (isEmpty())
            return Optional.empty();
        return Optional.of(played().get(played().size() - 1));
    }

    default Effect effect() {
        if (sameRank() && size() == 4) {
            return Effect.CLEAR;
        }
        return topCard().map(Card::getEffect).orElse(Effect.NONE);
    }

    default boolean sameRank() {
        return played().stream().map(Card::getRank).distinct().count() == 1;
    }

    static Round skip(Player player, Round prev) {
        return new SkippedTurn(prev, player);
    }

}
