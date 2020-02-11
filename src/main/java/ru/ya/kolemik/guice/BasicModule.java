package ru.ya.kolemik.guice;

import java.util.Arrays;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;

import ru.ya.kolemik.guice.api.BP;
import ru.ya.kolemik.guice.api.Config;
import ru.ya.kolemik.guice.api.Counter;
import ru.ya.kolemik.guice.api.Default;
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
        bind(Counter.class).annotatedWith(Default.class).to(CounterAtomic.class);
        bind(Hello.class).to(HelloSimple.class);
        MethodInterceptor interceptor = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                System.err.println(invocation.getMethod().getAnnotation(BP.class).value());
                System.err.println(invocation.getMethod().getName() + Arrays.toString(invocation.getArguments()));
                Throwable t = null;
                try {
                    return invocation.proceed();
                } catch (Throwable e) {
                    t = e;
                    throw t;
                } finally {
                    if (t != null) {
                        System.err.println("ERROR: " + t);
                        t.printStackTrace();
                    } else {
                        System.err.println("SUCCESS");
                    }
                }
            }
        };
        bindInterceptor(Matchers.subclassesOf(Hello.class), Matchers.annotatedWith(BP.class), interceptor);
        bindInterceptor(Matchers.subclassesOf(Counter.class), Matchers.annotatedWith(BP.class), interceptor);
    }
}