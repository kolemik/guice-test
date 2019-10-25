package ru.ya.kolemik.guice.impl;

import javax.inject.Named;
import javax.inject.Singleton;

import ru.ya.kolemik.guice.api.Counter;

@Singleton
@Named("sync")
public class CounterSync implements Counter {

    int c = 0;

    public CounterSync() { // LAZY INIT
        System.out.println("SYNC");
    }

    public synchronized void inc() {
        c++;
    }

    public synchronized void dec() {
        c--;
    }

    public synchronized int get() {
        return c;
    }
}
