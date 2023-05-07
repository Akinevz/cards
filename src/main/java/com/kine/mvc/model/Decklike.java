package com.kine.mvc.model;

import java.util.*;

import com.kine.mvc.model.cards.Card;

public interface Decklike {
    LinkedList<Card> cards();

    default List<Card> deal(int count) {
        var ret = new ArrayList<Card>();
        for (int i = 0; i < count; i++) {
            ret.add(cards().remove(0));
        }
        return ret;
    }
}
