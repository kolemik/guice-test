package ru.ya.kolemik.guice.impl;

public class StringAppender extends AbstractAppender<String> {

    @Override
    protected String accumulator(String a, String b) {
        return a!=null?a + b:b;
    }
}
