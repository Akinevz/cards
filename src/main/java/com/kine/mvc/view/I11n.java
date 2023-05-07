package com.kine.mvc.view;

public class I11n {
    public static String pluralise(String single) {
        return (!single.endsWith("s")) ? single + "s" : single + "es";
    }

    public static String capitalise(String string) {
        return string.toUpperCase();
    }
}