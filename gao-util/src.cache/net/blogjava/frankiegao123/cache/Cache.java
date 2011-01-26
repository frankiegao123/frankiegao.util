package net.blogjava.frankiegao123.cache;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>data cache</p>
 *
 * @author frankiegao123
 * 2011-1-12 10:53:04
 */
public interface Cache<K, V extends Serializable> {

    /**
     * <p>check whether the cache container is empty</p>
     *
     * @return
     * @author frankiegao123
     * 2011-1-12 10:54:11
     */
    public boolean isEmpty();

    /**
     * <p>check whether the cache container contains key</p>
     * @param key
     * @return
     * @author frankiegao123
     * 2011-1-12 10:54:11
     */
    public boolean containsKey(K key);

    /**
     * <p>get CacheHolder object accroding to key</p>
     *
     * @param key
     * @return
     * @author frankiegao123
     * 2011-1-12 11:13:16
     */
    public CacheHolder<V> getCache(K key);

    /**
     * <p>get value-cached accroding to key</p>
     *
     * @param key
     * @return
     * @author frankiegao123
     * 2011-1-12 11:13:46
     */
    public V get(K key);

    /**
     * <p>reset value of key</p>
     *
     * @param key
     * @param value
     * @return
     * @author frankiegao123
     * 2011-1-12 11:14:15
     */
    public CacheHolder<V> put(K key, CacheHolder<V> value);

    /**
     * <p>remove a cache object accroding to key</p>
     *
     * @param key
     * @return
     * @author frankiegao123
     * 2011-1-12 11:14:38
     */
    public CacheHolder<V> remove(K key);

    /**
     * <p>remove all cache object</p>
     *
     * @author frankiegao123
     * 2011-1-12 11:15:03
     */
    public void clear();

    /**
     * <p>size of cache container</p>
     *
     * @return
     * @author frankiegao123
     * 2011-1-12 11:15:19
     */
    public int size();

    /**
     * <p>get all key from cache container</p>
     *
     * @return
     * @author frankiegao123
     * 2011-1-12 11:18:29
     */
    public Set<K> keySet();
}
