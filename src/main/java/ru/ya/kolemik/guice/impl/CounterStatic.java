package ru.ya.kolemik.guice.impl;

import ru.ya.kolemik.guice.api.Counter;

public class CounterStatic implements Counter {

    static int c = 200;
    static final Object mutex = new Object();

    public CounterStatic() { // LAZY INIT
        System.out.println("STATIC: " + get());
    }

    public void inc() {
        synchronized (mutex) {
            c++;
        }
    }

    public void dec() {
        synchronized (mutex) {
            c--;
        }
    }

    public int get() {
        synchronized (mutex) {
            return c;
        }
    }
}
