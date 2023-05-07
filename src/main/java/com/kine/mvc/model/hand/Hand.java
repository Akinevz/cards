package com.kine.mvc.model.hand;

import java.util.List;
import java.util.Set;

import com.kine.mvc.model.cards.Card;
import com.kine.mvc.model.cards.Cards;

public interface Hand {

    /**
     * The players hand
     * 
     * @return cards the player is holding
     */
    Cards inHand();

    /**
     * Face down cards
     * 
     * @return face down cards
     */
    Cards faceDown();

    /**
     * Face up cards
     * 
     * @return face up cards
     */
    Cards faceUp();

    /**
     * Take cards
     * 
     * @param pile to pick up
     * @return number of cards taken
     */
    int pickup(List<Card> pile);

    /**
     * Indexes of the cards if they're present in the hand
     * 
     * @param cards collection to index
     * @return integers representing the position of the cards in the hand
     */
    CardIndexes inHand(Set<Card> cards);

    /**
     * Play a collection of cards at once
     * 
     * @return a list of cards removed from the hand
     */
    default List<Card> play(CardIndexes indexes) {
        return indexes.indexes().stream().map(inHand()::play).toList();
    }

    /**
     * Play a single card
     * 
     * @param index of the card
     * @return card that was removed
     */
    default Card play(int index) {
        return inHand().play(index);
    }

    /**
     * Whether the player has played all available cards
     * 
     * @return true when face down, face up and hand have been exhausted
     */
    boolean isWon();

}