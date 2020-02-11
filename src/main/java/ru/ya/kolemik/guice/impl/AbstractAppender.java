package ru.ya.kolemik.guice.impl;

import java.util.concurrent.atomic.AtomicReference;

import ru.ya.kolemik.guice.api.Appender;

public abstract class AbstractAppender<T> implements Appender<T> {
    protected final AtomicReference<T> data;

    public AbstractAppender() {
        this(null);
    }

    public AbstractAppender(T data) {
        this.data = new AtomicReference<T>(data);
    }

    @Override
    public Appender<T> append(T data) {
        this.data.accumulateAndGet(data, (a, b) -> accumulator(a, b));
        return this;
    }
    
    protected abstract T accumulator(T a, T b);

    @Override
    public T get() {
        return this.data.get();
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
