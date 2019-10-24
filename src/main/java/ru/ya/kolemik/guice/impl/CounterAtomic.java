package ru.ya.kolemik.guice.impl;

import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Singleton;

import ru.ya.kolemik.guice.api.Counter;

@Singleton
public class CounterAtomic implements Counter {

    AtomicInteger c = new AtomicInteger(100);

    public CounterAtomic() { // LAZY INIT
        System.out.println("ATOMIC");
    }

    public void inc() {
        c.incrementAndGet();
    }

    public void dec() {
        c.decrementAndGet();
    }

    public int get() {
        return c.get();
    }
}
