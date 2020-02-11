package ru.ya.kolemik.guice.impl;

public class IntAppender extends AbstractAppender<Integer> {

    @Override
    protected Integer accumulator(Integer a, Integer b) {
        return a!=null?a + b:b;
    }
}
