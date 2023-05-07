package com.kine.mvc.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.stream.Stream;

@FunctionalInterface
public interface Inputs<T extends CharSequence> {
    T[] data();

    default InputStream stream() {
        var streams = Stream.of(data()).map(CharSequence::toString);
        var string = streams.reduce((a,b) -> a+"\n"+b).orElse("");
        var byteStream = new ByteArrayInputStream(string.getBytes());
        return byteStream;
    }
}
