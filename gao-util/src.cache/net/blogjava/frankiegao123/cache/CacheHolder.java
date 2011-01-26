package net.blogjava.frankiegao123.cache;

import java.io.Serializable;

/**
 * <p>cache holder</p>
 *
 * @author frankiegao123
 * 2011-1-12 上午11:19:04
 */
public interface CacheHolder<T extends Serializable> {

    /**
     * <p>获取缓存容器中被缓存的对象</p>
     *
     * @return
     * @author frankiegao123
     * 2010-11-18 上午11:26:16
     */
    T get();

    /**
     * <p>缓存是否有效</p>
     *
     * @return
     * @author frankiegao123
     * 2010-11-18 上午11:26:45
     */
    boolean isValid();

    /**
     * <p>获取缓存命中数</p>
     *
     * @return
     * @author frankiegao123
     * 2010-11-18 上午11:27:04
     */
    int getHitCount();

    /**
     * <p>增加一次缓存命中数</p>
     *
     * @return
     * @author frankiegao123
     * 2010-11-18 上午11:27:30
     */
    int incrementHitCount();

    /**
     * <p>缓存放入缓存容器的时间</p>
     *
     * @return
     * @author frankiegao123
     * 2010-11-18 上午11:28:12
     */
    long getCreateTime();
}
