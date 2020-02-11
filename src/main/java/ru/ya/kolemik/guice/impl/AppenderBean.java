package ru.ya.kolemik.guice.impl;

import javax.inject.Inject;

import ru.ya.kolemik.guice.api.Appender;

public class AppenderBean {

    @Inject
    Appender<Integer> appInt;

    @Inject
    Appender<String> appString;

    @Inject
    Appender appDefault;

    @Inject
    Appender<?> appAny;

    @Inject
    Appender<Object> appObject;

    public void main() {
        System.out.println(appInt.append(156).append(567).get());
        System.out.println(((Appender<String>)appDefault).append("156").append("qwe").get()); // success work with strings
        try {
            System.out.println(((Appender<Object>)appDefault).append(new Object()).get());
        } catch (Exception e) {
            System.out.println("ERROR while using not supported parameters: " + e.getMessage());
        }
//        System.out.println(appAny.append("156").append(new Object()).get()); // compile error here -- you must specify exact classes to use parametrized methods
        System.out.println(((Appender<String>)appAny).append("156").append("qwe").get()); // success work with strings
        System.out.println(appObject.append("156").append(new Object()).get());
        System.out.println("===================================");
        System.out.println(appInt.getName());
        System.out.println(appString.getName());
        System.out.println(appDefault.getName());
        System.out.println(appAny.getName());
        System.out.println(appObject.getName());
    }
}
