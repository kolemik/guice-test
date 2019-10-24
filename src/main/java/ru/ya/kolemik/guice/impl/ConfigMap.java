package ru.ya.kolemik.guice.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Singleton;

import ru.ya.kolemik.guice.api.Config;

@Singleton
public class ConfigMap implements Config {
    
    private final Map<String, String> props = new ConcurrentHashMap<String, String>();

    public ConfigMap() {
        System.getProperties().entrySet().forEach(e -> {
            props.put(e.getKey().toString(), e.getValue().toString());
        });
        System.out.println("STARTED WITH: " + props);
    }

    public String getProperty(String name) {
        return props.get(name);
    }

}
