package net.blogjava.frankiegao123.cache.holder;

import java.io.Serializable;

/**
 * <p>���ڲ��ԵĻ������ִ����</p>
 *
 * @author frankiegao123
 * 2010-11-18 ����11:35:27
 */
public class ExpireCacheHolder<T extends Serializable> extends AbstractCacheHolder<T> {

    private long expireTime;

    /**
     * <p>�����ڵĻ������ִ��������</p>
     *
     * @param object        ��Ҫ����Ķ���
     */
    protected ExpireCacheHolder(T object) {
        this(object, 0);
    }

    /**
     * <p>������ʱ��Ļ������ִ��������</p>
     *
     * @param object        ��Ҫ����Ķ���
     * @param expireSecond  ����ʱ�䣨�룩
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
