package com.kine.mvc.model.cards;

import com.kine.mvc.controller.I11n;
import com.kine.mvc.model.effects.Effect;

public class Card {

    private final Suit suit;
    private final Rank rank;

    Card(Suit s, Rank r) {
        this.suit = s;
        this.rank = r;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Effect getEffect() {
        return rank.effect();
    }

    public static String show(Card card) {
        var rank = card.rank;
        var suit = card.suit;
        return String.format("%s of %s: %s",
                I11n.capitalise(rank.get()),
                I11n.pluralise(suit.get()),
                card.getEffect().description());
    }

    public final int beats(Card card) {
        var s1 = this.rank;
        var s2 = card.rank;
        return Rank.beatsComparator().compare(s1, s2);
    };

}