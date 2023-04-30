package com.kine.mvc.view;

import java.util.*;
import java.util.function.*;

import com.kine.mvc.controller.InputAggregator;

public class Input {

    public static Function<Integer, Predicate<Integer>> rangeCheck = (top) -> (i) -> (i >= 0 && i < top);

    public static Function<String, Optional<? extends Integer>> intParser = (str) -> {
        try {
            int tryParsed = Integer.parseInt(str);
            return Optional.of(tryParsed);
        } catch (NumberFormatException pe) {
            return Optional.empty();
        }
    };

    public static String readLine() {
        return System.console().readLine();
    }

    public static <T> T readParsed(Function<String, Optional<? extends T>> parser) {
        return readParsed(parser, Optional.empty());
    }

    public static <T> T readParsed(Function<String, Optional<? extends T>> parser, String prompt) {
        return readParsed(parser, Optional.of(prompt));
    }

    public static <T> T readParsed(Function<String, Optional<? extends T>> parser, Optional<String> prompt) {
        Consumer<String> print = (String str) -> System.console().printf("%s", str);
        prompt.ifPresent(print);
        var line = readLine();
        var parsed = parser.apply(line);
        if (parsed.isPresent()) {
            return parsed.get();
        }
        return readParsed(parser, prompt);
    }

    public static <T> InputAggregator<T> readAggregate(
            Function<String, Optional<? extends T>> parser,
            Function<Integer, String> prompt,
            Supplier<Integer> count) {
        List<T> col = new ArrayList<T>();
        while (count.get() - col.size() > 0) {
            var read = readParsed(parser, prompt.apply(count.get()));
            col.add(read);
        }

        return () -> col;
    }
}