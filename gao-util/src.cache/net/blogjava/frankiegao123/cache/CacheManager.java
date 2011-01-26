package net.blogjava.frankiegao123.cache;

import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class CacheManager {

    private static CacheManager manager;

    private Map<String, Cache<?, ? extends Serializable>> cacheService;

    private CacheManager(Map<String, Cache<?, ? extends Serializable>> cacheService) {
        this.cacheService = cacheService;
    }

    public static CacheManager getCacheManager() {
        return manager;
    }

    @SuppressWarnings("unchecked")
    public <K, V extends Serializable> Cache<K, V> addCache(String key, Cache<K, V> cache) {
        return (Cache<K, V>)cacheService.put(key, cache);
    }

    public Cache<?, ?> getCache(String key) {
        return cacheService.get(key);
    }

    public <K, V extends Serializable> Cache<K, V> getCache(String key, Class<K> keyClass, Class<V> valueClass) {
        return (Cache<K, V>)getCache(key);
    }
}
