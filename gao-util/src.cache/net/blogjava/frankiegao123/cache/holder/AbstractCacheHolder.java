package net.blogjava.frankiegao123.cache.holder;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import net.blogjava.frankiegao123.cache.CacheHolder;

public abstract class AbstractCacheHolder<T extends Serializable> implements CacheHolder<T> {

    private T object;
    private AtomicInteger hitCount;
    private long createTime;

    public AbstractCacheHolder(T object) {
        this.object = object;
        this.createTime = System.currentTimeMillis();
        this.hitCount = new AtomicInteger(0);
    }

    public long getCreateTime() {
        return createTime;
    }

    public T get() {
        return object;
    }

    public int incrementHitCount() {
        return hitCount.incrementAndGet();
    }

    public int getHitCount() {
        return hitCount.get();
    }
}
