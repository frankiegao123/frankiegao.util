package net.blogjava.frankiegao123.cache.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import net.blogjava.frankiegao123.cache.CacheHolder;

public class LockedMapCache<K, V extends Serializable> extends MapCache<K, V> {
    
    protected ReadWriteLock rwlock;
    protected Lock readLock;
    protected Lock writeLock;

    public LockedMapCache(Map<K, CacheHolder<V>> cache) {
        super(cache);
        this.rwlock = new ReentrantReadWriteLock();
        this.readLock = this.rwlock.readLock();
        this.writeLock = this.rwlock.writeLock();
    }

    //////////////////////////////////////
    // read lock area
    //////////////////////////////////////
    public boolean isEmpty() {
        readLock.lock();
        try {
            return super.isEmpty();
        } finally {
            readLock.unlock();
        }
    }

    public Set<K> keySet() {
        readLock.lock();
        try {
            return new HashSet<K>(super.keySet());
        } finally {
            readLock.unlock();
        }
    }

    public int size() {
        readLock.lock();
        try {
            return super.size();
        } finally {
            readLock.unlock();
        }
    }

    public boolean containsKey(K key) {
        readLock.lock();
        try {
            return super.containsKey(key);
        } finally {
            readLock.unlock();
        }
    }

    public V get(K key) {
        CacheHolder<V> holder = null;
        readLock.lock();
        try {
            holder = cache.get(key);
        } finally {
            readLock.unlock();
        }
        return incrementHitCountAndGet(holder);
    }

    public CacheHolder<V> getCache(K key) {
        readLock.lock();
        try {
            return super.getCache(key);
        } finally {
            readLock.unlock();
        }
    }

    //////////////////////////////////////
    // write lock area
    //////////////////////////////////////
    public void clear() {
        writeLock.lock();
        try {
            super.clear();
        } finally {
            writeLock.unlock();
        }
    }

    public CacheHolder<V> put(K key, CacheHolder<V> value) {
        writeLock.lock();
        try {
            return super.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public CacheHolder<V> remove(K key) {
        writeLock.lock();
        try {
            return super.remove(key);
        } finally {
            writeLock.unlock();
        }
    }
}
