package com.kine.mvc;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.kine.mvc.model.cards.Rank;

class TestRank {

    @Test
    void testRanksComparator() {
        for (Rank rank : Rank.values()) {
            for (Rank rank2 : Rank.values()) {
                System.out.println(String.format("%s beats %s = %s", rank.get(), rank2.get(),
                        rank.beats(rank2) ? "true" : "false"));
            }
        }
        Stream<Executable> stream = Stream.of(Rank.values()).map(Rank.Ace::beats)
                .map(t -> () -> assertTrue(t));
        assertAll("Ace beats all", stream);
        Stream<Executable> stream2 = Stream.of(Rank.values()).map(Rank.Two::beats)
                .map(t -> () -> assertTrue(t));
        assertAll("Two beats all", stream2);
        Stream<Executable> stream3 = Stream.of(Rank.values()).filter(s -> s.compareTo(Rank.Seven) > 0)
                .map(r -> () -> assertFalse(Rank.Seven.beats(r)));
        assertAll("Seven beats none above seven", stream3);
        Stream<Executable> stream4 = Stream.of(Rank.values()).filter(s -> s.compareTo(Rank.Seven) > 0)
                .filter(s -> !s.equals(Rank.Ace))
                .map(r -> () -> assertFalse(r.beats(Rank.Seven)));
        assertAll("All above seven do not beat seven except Ace", stream4);
    }
}