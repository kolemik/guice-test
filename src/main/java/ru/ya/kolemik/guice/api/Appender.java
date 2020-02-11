package ru.ya.kolemik.guice.api;

public interface Appender <T> {
    Appender<T> append(T data);
    T get();
    String getName();
}
