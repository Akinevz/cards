package com.kine.mvc.model.cards;

import java.util.Comparator;

import com.kine.mvc.model.effects.Effect;

public enum Rank {
    Two("Two", true),
    Three("Three"),
    Four("Four"),
    Five("Five"),
    Six("Six"),
    Seven("Seven", Effect.LOWER),
    Eight("Eight", Effect.BLOCK),
    Nine("Nine"),
    Ten("Ten", Effect.CLEAR),
    Jack("Jack"),
    Queen("Queen"),
    King("King"),
    Ace("Ace", true);

    Rank(String name) {
        this(name, false);
    }

    Rank(String name, boolean trump) {
        this(name, (trump) ? Effect.TRUMP : Effect.NONE);
    }

    Rank(String name, Effect effect) {
        this.name = name;
        this.effect = effect;
    }

    public String get() {
        return name;
    }

    public Effect effect() {
        return effect;
    }

    private final String name;
    private final Effect effect;

    public static Comparator<Rank> beatsComparator() {
        return new Comparator<Rank>() {

            @Override
            public int compare(Rank r1, Rank r2) {
                var t1 = r1.effect.equals(Effect.TRUMP);
                // if same
                if (r1.equals(r2))
                    return 0;
                // if seven, must compare less than
                if (r1.effect.equals(Effect.LOWER)) {
                    return r1.compareTo(r2) < 0 ? +1 : -1;
                }
                // if trump can lay
                if (t1)
                    return +1;
                // else compare by rank
                return r1.compareTo(r2);
            }

        };
    }

    public boolean beats(Rank rank) {
        return this.compareTo(rank) != -1;
    }

}