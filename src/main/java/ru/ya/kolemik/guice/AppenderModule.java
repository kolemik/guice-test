package ru.ya.kolemik.guice;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

import ru.ya.kolemik.guice.api.Appender;
import ru.ya.kolemik.guice.impl.AbstractAppender;
import ru.ya.kolemik.guice.impl.AppenderBean;
import ru.ya.kolemik.guice.impl.IntAppender;
import ru.ya.kolemik.guice.impl.StringAppender;

public class AppenderModule extends AbstractModule {
    
    @Override
    protected void configure() {
        bind(new TypeLiteral<Appender<Integer>>() {}).to(IntAppender.class);
        bind(new TypeLiteral<Appender<Object>>() {}).toInstance(new AbstractAppender<Object>() {
            protected Object accumulator(Object a, Object b) {
                return a!= null?(String.valueOf(a) + String.valueOf(b)):b;
            }
            @Override
            public String getName() {
                return "Qwe";
            }
        });
        bind(new TypeLiteral<Appender<?>>() {}).to(StringAppender.class);
        bind(new TypeLiteral<Appender<String>>() {}).to(StringAppender.class);
        bind(Appender.class).to(StringAppender.class);
        bind(AppenderBean.class);
    }
}