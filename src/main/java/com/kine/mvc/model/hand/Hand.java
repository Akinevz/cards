package com.kine.mvc.model.hand;

import java.util.List;

import com.kine.mvc.model.cards.*;

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
     * @param cards to pick up
     * @return number of cards taken
     */
    int pickup(Decklike cards);

    /**
     * Indexes of the cards if they're present in the hand
     * @param cards collection to index
     * @return integers representing the position of the cards in the hand
     */
    CardIndexes inHand(Cards cards);

    /**
     * 
     * @return
     */
    List<Card> play(CardIndexes indexes);

    /**
     * Whether the player has played all available cards
     * 
     * @return true when face down, face up and hand have been exhausted
     */
    boolean isWon();

}