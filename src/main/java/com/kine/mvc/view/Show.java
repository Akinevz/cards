package com.kine.mvc.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.kine.mvc.controller.Round;
import com.kine.mvc.model.cards.Card;
import com.kine.mvc.model.cards.Cards;
import com.kine.mvc.model.player.Player;

public class Show {
    public static String player(Player p) {
        return Player.show(p);
    }

    public static String card(Card c) {
        return Card.show(c);
    }

    public static String cards(Cards c) {
        return Cards.show(c);
    }

    public static String round(Round r) {
        return Round.show(r);
    }

    public static <T> String forSingle(T obj) {
        if (obj instanceof Round r) {
            return Show.round(r);
        }
        if (obj instanceof Cards c) {
            return Show.cards(c);
        }
        if (obj instanceof Card c) {
            return Show.card(c);
        }
        if (obj instanceof Player p) {
            return Show.player(p);
        }
        return obj.toString();
    }

    public static <T> String forT(Collection<T> inCol) {
        var col = new ArrayList<T>(inCol);
        Function<Integer, String> indexed = (i) -> String.format("%d: %s", i, Show.forSingle(col.get(i)));
        return Stream.iterate(0, i -> i + 1)
                .limit(inCol.size())
                .map(indexed)
                .collect(Collectors.joining("\n"));
    }
}
