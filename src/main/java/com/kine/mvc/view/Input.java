package com.kine.mvc.view;

import java.util.*;
import java.util.function.*;

public class Input {

    public static ThrowingFunction<String, Integer> intParseThrowing = Integer::parseInt;

    public static Function<Integer, Predicate<Integer>> rangeCheck = (top) -> (i) -> (i >= 0 && i < top);

    public static Parser<Integer> intParser() {
        return s -> intParseThrowing.try_catch(Optional::of, e -> Optional.empty()).apply(s);
    }

    private static Scanner sc;

    public static String readLine() {
        if (Objects.nonNull(sc))
            return sc.nextLine();
        sc = new Scanner(System.in);
        return readLine();
    }

    public static <T> T readParsed(Parser<T> parser) {
        return readParsed(parser, Optional.empty());
    }

    public static <T> T readParsed(Parser<T> parser, String prompt) {
        return readParsed(parser, Optional.of(prompt));
    }

    public static <T> T readParsed(Parser<T> parser, Optional<String> prompt) {
        Consumer<String> print = (String str) -> System.out.printf("%s", str);
        prompt.ifPresent(print);
        var line = readLine();
        Optional<? extends T> parsed;
        try {
            parsed = parser.parse(line);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (parsed.isPresent()) {
            return parsed.get();
        }
        System.out.println();
        return readParsed(parser, prompt);
    }

    public static <T> InputAggregator<T> readAggregate(
            Parser<T> parser,
            Function<Set<T>, String> prompt,
            int size) {
        Set<T> col = new HashSet<T>();
        while (size - col.size() > 0) {
            try {
                var read = readParsed(parser, prompt.apply(col));
                col.add(read);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                sc = new Scanner(System.in);
            }
        }

        return () -> col;
    }

    public static <T> Set<T> readMulti(Collection<T> in, Parser<T> parser,
            Function<Set<T>, String> prompt) {
        Set<T> selected = new HashSet<>();
        var inOriginalSize = in.size();
        while (selected.size() < inOriginalSize) {
            try {
                System.out.println(prompt.apply(selected));
                System.out.printf("Available:\n%s\n", Show.forT(in));
                String input = sc.nextLine().trim();
                Optional<? extends T> parsed = parser.parse(input);
                if (parsed.isEmpty()) {
                    return selected;
                }
                var value = parsed.get();
                if (!selected.add(value)) {
                    selected.remove(value);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return selected;
    }
}