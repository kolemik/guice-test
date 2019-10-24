package ru.ya.kolemik.guice;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import ru.ya.kolemik.guice.api.Config;
import ru.ya.kolemik.guice.api.Counter;
import ru.ya.kolemik.guice.api.Hello;
import ru.ya.kolemik.guice.impl.ConfigMap;
import ru.ya.kolemik.guice.impl.CounterAtomic;
import ru.ya.kolemik.guice.impl.CounterProvider;
import ru.ya.kolemik.guice.impl.CounterStatic;
import ru.ya.kolemik.guice.impl.CounterSync;
import ru.ya.kolemik.guice.impl.HelloSimple;

public class BasicModule extends AbstractModule {
    
    @Override
    protected void configure() {
        bind(Config.class).to(ConfigMap.class);
        bind(Counter.class).annotatedWith(Names.named("sync")).to(CounterSync.class);
        bind(Counter.class).annotatedWith(Names.named("atomic")).to(CounterAtomic.class);
        bind(Counter.class).annotatedWith(Names.named("static")).to(CounterStatic.class);
        bind(Counter.class).toProvider(CounterProvider.class);
        bind(Hello.class).to(HelloSimple.class);
    }
}