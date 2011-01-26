package net.blogjava.frankiegao123.cache.impl;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

import net.blogjava.frankiegao123.cache.CacheHolder;

public class ConcurrentCache<K, V extends Serializable> extends MapCache<K, V> {

    public ConcurrentCache() {
        super(new ConcurrentHashMap<K, CacheHolder<V>>());
    }
}
