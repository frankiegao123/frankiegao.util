package net.blogjava.frankiegao123.cache.holder;

import java.io.Serializable;

/**
 * <p>过期策略的缓存对象执有器</p>
 *
 * @author frankiegao123
 * 2010-11-18 上午11:35:27
 */
public class ExpireCacheHolder<T extends Serializable> extends AbstractCacheHolder<T> {

    private long expireTime;

    /**
     * <p>不过期的缓存对象执有器构造</p>
     *
     * @param object        需要缓存的对象
     */
    protected ExpireCacheHolder(T object) {
        this(object, 0);
    }

    /**
     * <p>带过期时间的缓存对象执有器构造</p>
     *
     * @param object        需要缓存的对象
     * @param expireSecond  过期时间（秒）
     */
    public ExpireCacheHolder(T object, int expireSecond) {
        super(object);
        if(expireSecond > 0) {
            this.expireTime = getCreateTime() + 1000L * expireSecond;
        }
    }

    public boolean isValid() {
        if(expireTime <= 0) {
            return true;
        }
        return (System.currentTimeMillis() < expireTime);
    }
}
