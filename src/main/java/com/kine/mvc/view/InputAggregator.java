package com.kine.mvc.view;

import java.util.Set;

@FunctionalInterface
public interface InputAggregator<T> {

    Set<T> get();

}
