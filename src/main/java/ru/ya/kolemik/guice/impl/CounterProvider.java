package ru.ya.kolemik.guice.impl;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;

import ru.ya.kolemik.guice.api.Config;
import ru.ya.kolemik.guice.api.Counter;
import ru.ya.kolemik.guice.api.Default;

public class CounterProvider implements Provider<Counter> {

    @Inject
    private Injector injector;

    @Inject
    private Config config;

    public Counter get() { // called every time an injection performed -- so may return different instances during runtime
        String counterProp = config.getProperty("counter");
        System.out.println("PROVIDER");
        if (counterProp != null && !counterProp.isEmpty() && injector.getBindings().containsKey(Key.get(Counter.class, Names.named(counterProp)))) {
            return injector.getInstance(Key.get(Counter.class, Names.named(counterProp)));
        } else {
            return injector.getInstance(Key.get(Counter.class, Default.class));
        }
    }
}
