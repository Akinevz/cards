package com.kine.mvc.model.cards;

public enum Suit {
    A("Heart"), B("Club"), C("Spade"), D("Diamond");

    Suit(String name) {
        this.name = name;
    }

    String get() {
        return name;
    }

    private final String name;
}