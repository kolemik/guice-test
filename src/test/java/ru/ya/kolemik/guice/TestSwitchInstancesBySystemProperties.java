package ru.ya.kolemik.guice;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.google.inject.Guice;
import com.google.inject.Injector;

import ru.ya.kolemik.guice.api.Hello;

@RunWith(Parameterized.class)
public class TestSwitchInstancesBySystemProperties {
    private Injector injector;
    private String counter;

    @Parameters(name = "counter = {0}")
    public static Collection<String> names() {
        return Arrays.asList("atomic", "static", "sync");
    }

    public TestSwitchInstancesBySystemProperties(String counterName) {
        System.setProperty("counter", counterName);
        counter = counterName;
        injector = Guice.createInjector(new BasicModule());
    }

    @Test
    public void test() {
        for (int i = 0; i < 3; i++) {
            Hello hello = injector.getInstance(Hello.class);
            String response = hello.sayHello("Max");
            System.out.println("RESP: " + response);
            switch (counter) {
            case "sync":
                assertTrue(response.contains("\t" + (i + 1) + "\t"));
                break;
            case "atomic":
                assertTrue(response.contains("\t" + (i + 101) + "\t"));
                break;
            case "static":
                assertTrue(response.contains("\t" + (i + 201) + "\t"));
                break;
            default:
                fail("unexpected counter: " + counter);
            }
        }
    }

}
