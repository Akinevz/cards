package com.kine.mvc.model.player;

import com.kine.mvc.model.cards.Cards;
import com.kine.mvc.model.hand.Hand;

public interface Strategy {

    Cards play(Cards toBeat, Hand with);

    Cards initialCards(Hand from);
}
