package ru.ya.kolemik.guice;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import ru.ya.kolemik.guice.impl.AppenderBean;

public class TestTypeLiteral {
    private Injector injector;

    @Before
    public void setUp() {
        injector = Guice.createInjector(new AppenderModule());
    }

    @Test
    public void test() {
        AppenderBean appenderBean = injector.getInstance(AppenderBean.class);
        appenderBean.main();
    }

}
