package com.kine.mvc.model.effects;

public enum Effect {
    BLOCK("Prevents the next player from playing a round"),
    CLEAR("Drops the current discard deck"),
    LOWER("Must play a card lower than or equal to a 7"),
    TRUMP("This card can be played after any card"),
    NONE();

    String description;

    Effect(String description) {
        this.description = description;
    }

    Effect() {
        this("No special effect");
    }

    static String show(Effect e) {
        return e.description();
    }

    public String description() {
        return description;
    }

}
