package com.kine.mvc.model.cards;

import java.util.Comparator;

import com.kine.mvc.model.effects.Effect;
import com.kine.mvc.view.I11n;
import com.kine.mvc.view.Show;

public class Card {

    private final Suit suit;
    private final Rank rank;

    public Card(Suit s, Rank r) {
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
        return String.format("%s of %s",
                I11n.capitalise(rank.get()),
                I11n.pluralise(suit.get()));
    }

    public final boolean beats(Card card) {
        // var s1 = this.rank;
        // if (card == null)
        // return +1;
        // var s2 = card.rank;
        // return Rank.beatsComparator().compare(s1, s2);
        return (card == null) ? false : this.rank.beats(card.rank);
    };

    public static final Comparator<Card> comparator = new Comparator<Card>() {

        @Override
        public int compare(final Card o1, final Card o2) {
            return o1.rank.compareTo(o2.rank);
        }

    };

    @Override
    public String toString() {
        return Show.card(this);
    }
}