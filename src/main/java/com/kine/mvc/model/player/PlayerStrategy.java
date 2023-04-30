package com.kine.mvc.model.player;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.kine.mvc.controller.InputAggregator;
import com.kine.mvc.model.cards.Card;
import com.kine.mvc.model.cards.Cards;
import com.kine.mvc.model.hand.Hand;
import com.kine.mvc.view.Input;

public class PlayerStrategy implements Strategy {

    private Player player;

    public PlayerStrategy(Player player) {
        this.player = player;
    }

    public Cards choose(Cards last, Hand hand) {
        var cards = last.list();
        System.out.println(String.format("%s: %s", "Turn", Player.show(player)));
        System.out.println(String.format("%s: %s", "Last played", Cards.show(cards)));
        System.out.println(String.format("%s:\n%s", "Your hand", Cards.show(hand.cards())));
        Predicate<Integer> check = Input.rangeCheck.apply(hand.cards().size());
        InputAggregator<Integer> agg = Input.readAggregate(
                Input.intParser.andThen(s -> s.filter(check)),
                count -> String.format("%s (%s)", "Select a card to play",
                        String.format("0 .. %d", count)),
                () -> hand.cards().size());
        List<Integer> indices = agg.get();
        var toPlay = indices.stream().map(hand.cards()::get).collect(Collectors.toList());
        return () -> toPlay;
    }

    public void determineFaceUp(int handSize, Hand hand) {

        class ChooseShown {
            List<Card> remaining(int chosen) {
                var cardsLeft = hand.cards();
                var toPlay = handSize - chosen;
                if (!(toPlay > 0))
                    return new LinkedList<>();
                System.out.println(String.format("%s (%s):",
                        "Cards in hand to play face up",
                        String.format("%d left", handSize)));
                var show = Cards.show(cardsLeft);
                System.out.println(show);
                var prompt = (String.format("Select number 0 .. %d", cardsLeft.size()));

                int input = Input.readParsed(
                        Input.intParser.andThen(s -> s.filter(Input.rangeCheck.apply(cardsLeft.size()))), prompt);
                var cardChosen = cardsLeft.remove(input);
                var ret = new LinkedList<>(Arrays.asList(cardChosen));
                ret.addAll(remaining(chosen + 1));
                return ret;
            }
        }

        var toShow = new ChooseShown().remaining(0);
        player.getHand().faceUp().addAll(toShow);
    }

    @Override
    public Cards play(Cards toBeat, Hand with) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'play'");
    }

    @Override
    public Cards initialCards(Hand from) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initialCards'");
    }

}
