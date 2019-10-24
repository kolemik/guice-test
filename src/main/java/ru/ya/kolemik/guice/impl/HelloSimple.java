package ru.ya.kolemik.guice.impl;

import javax.inject.Inject;

import ru.ya.kolemik.guice.api.Counter;
import ru.ya.kolemik.guice.api.Hello;

public class HelloSimple implements Hello {

    @Inject
    private Counter counter;

    public String sayHello(String name) {
        counter.inc();
        return "[SIMPLE] Hello, " + name + "!\t" + counter.get() + "\t" + hashCode();
    }

}
