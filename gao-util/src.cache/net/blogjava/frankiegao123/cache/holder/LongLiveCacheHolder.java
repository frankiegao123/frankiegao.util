package net.blogjava.frankiegao123.cache.holder;

import java.io.Serializable;

public class LongLiveCacheHolder<T extends Serializable> extends ExpireCacheHolder<T> {

    public LongLiveCacheHolder(T object) {
        super(object);
    }
}
