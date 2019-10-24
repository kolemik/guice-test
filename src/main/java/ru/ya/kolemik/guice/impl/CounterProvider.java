package ru.ya.kolemik.guice.impl;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;

import ru.ya.kolemik.guice.api.Config;
import ru.ya.kolemik.guice.api.Counter;

public class CounterProvider implements Provider<Counter> {

    @Inject
    private Injector injector;

    @Inject
    private Config config;

    public Counter get() { // called every time an injection performed -- so may return different instances during runtime
        String counterProp = config.getProperty("counter");
        System.out.println("PROVIDER");
        return injector.getInstance(Key.get(Counter.class, Names.named(counterProp)));
    }
}
