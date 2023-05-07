package com.kine.mvc.view;

import java.util.Optional;
import java.util.function.Function;

@FunctionalInterface
interface ThrowingFunction<T, R> {
    R apply(T v) throws Exception;

    default <F> Function<T,Optional<? extends F>> try_catch(Function<R,Optional<F>> ifPresent, Function<Exception,Optional<F>> isAbsent){
        return t ->{
            try {
                return ifPresent.apply(apply(t));
            } catch (Exception e) {
                return isAbsent.apply(e);
            }
        };
    }
}