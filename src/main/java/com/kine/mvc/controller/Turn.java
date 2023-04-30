package com.kine.mvc.controller;

import java.util.Optional;

import com.kine.mvc.model.cards.Card;
import com.kine.mvc.model.cards.Cards;
import com.kine.mvc.model.effects.Effect;
import com.kine.mvc.model.player.Player;

/**
 * A complete turn that has been taken in the game.
 * 
 * On a turn some cards are played.
 * 
 * Player needs to place cards according to the rules.
 * 
 * At the end of the turn if no cards are placed the player
 * picks up the deck.
 */
public interface Turn {
    /**
     * Descriptor of the play that occurred.
     */
    String info();

    /**
     * Reference to the previous turn.
     */
    Turn previous();

    /**
     * Player that plays on this turn.
     */
    Player player();

    /**
     * The cards that were played
     */
    Cards cards();

    public static String show(Turn round) {
        return round.info();
    }

    default Effect effect() {
        return Optional.ofNullable(cards()).map(Cards::getEffect).orElse(Effect.NONE);
    }

    default Optional<Card> topCard() {
        return Optional.ofNullable(cards()).flatMap(Cards::topCard);
    }

    static Turn forPlayer(Player player, Table arrangement) {
        var lastTurn = arrangement.getLastTurn();
        switch (arrangement.getLastTurn().effect()) {
            case BLOCK:
                // return new NonTurn(player);
            case LOWER:
                // return new LowerThanTurn(player, arrangement.getLastTurn().topCard());
            default:
                return new Turn() {

                    Cards playerCards = player.getStrategy().choose(lastTurn.cards(), player.getHand());

                    @Override
                    public String info() {
                        return String.format("%s plays %s", Player.show(player), Cards.show(cards()));
                    }

                    @Override
                    public Turn previous() {
                        return lastTurn;
                    }

                    @Override
                    public Player player() {
                        return player;
                    }

                    @Override
                    public Cards cards() {
                        return playerCards;
                    }

                };
        }
    }

}
