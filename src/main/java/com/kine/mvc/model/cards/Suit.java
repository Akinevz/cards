package com.kine.mvc.model.cards;

public enum Suit {
    Heart("Heart"), Club("Club"), Spade("Spade"), Diamond("Diamond");

    Suit(String name) {
        this.name = name;
    }

    String get() {
        return name;
    }

    private final String name;
}