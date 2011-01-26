package net.blogjava.frankiegao123.cache.impl;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import net.blogjava.frankiegao123.cache.CacheHolder;
import net.blogjava.frankiegao123.cache.Cache;

public class MapCache<K, V extends Serializable> implements Cache<K, V> {

    protected Map<K, CacheHolder<V>> cache;

    public MapCache(Map<K, CacheHolder<V>> cache) {
        this.cache = cache;
    }

    public void clear() {
        cache.clear();
    }

    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    public V get(K key) {
        CacheHolder<V> holder = cache.get(key);
        return incrementHitCountAndGet(holder);
    }

    public CacheHolder<V> getCache(K key) {
        return cache.get(key);
    }

    public boolean isEmpty() {
        return cache.isEmpty();
    }

    public Set<K> keySet() {
        return cache.keySet();
    }

    public CacheHolder<V> put(K key, CacheHolder<V> value) {
        return cache.put(key, value);
    }

    public CacheHolder<V> remove(K key) {
        return cache.remove(key);
    }

    public int size() {
        return cache.size();
    }

    protected V incrementHitCountAndGet(CacheHolder<V> holder) {
        if(holder != null && holder.isValid()) {
            holder.incrementHitCount();
            return holder.get();
        }
        return null;
    }
}
