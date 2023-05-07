package com.kine.mvc.view;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@FunctionalInterface
public interface Parser<R> {
    Optional<? extends R> parse(String s) throws Exception;

    default <V> Parser<V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (String t) -> {
            try {
                return parse(t).map(after);
            } catch (Exception e) {
                return Optional.empty();
            }
        };
    }
}