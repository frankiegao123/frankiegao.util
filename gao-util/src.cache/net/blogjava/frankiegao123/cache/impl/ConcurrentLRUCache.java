package net.blogjava.frankiegao123.cache.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import net.blogjava.frankiegao123.cache.CacheHolder;

public class ConcurrentLRUCache<K, V extends Serializable> extends LockedMapCache<K, V> {

    public ConcurrentLRUCache(int max) {
        super(new LRULinkedHashMap<K, CacheHolder<V>>(max));
    }

    protected static class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {

        private static final long serialVersionUID = 1L;
        private int max;

        protected LRULinkedHashMap(int max) {
            this.max = max;
        }

        @Override
        protected boolean removeEldestEntry(Entry<K, V> eldest) {
            return size() > max;
        }
        
        protected int getMax() {
            return max;
        }
    }
}