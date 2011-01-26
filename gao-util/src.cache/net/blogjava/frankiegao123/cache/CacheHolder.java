package net.blogjava.frankiegao123.cache;

import java.io.Serializable;

/**
 * <p>cache holder</p>
 *
 * @author frankiegao123
 * 2011-1-12 ����11:19:04
 */
public interface CacheHolder<T extends Serializable> {

    /**
     * <p>��ȡ���������б�����Ķ���</p>
     *
     * @return
     * @author frankiegao123
     * 2010-11-18 ����11:26:16
     */
    T get();

    /**
     * <p>�����Ƿ���Ч</p>
     *
     * @return
     * @author frankiegao123
     * 2010-11-18 ����11:26:45
     */
    boolean isValid();

    /**
     * <p>��ȡ����������</p>
     *
     * @return
     * @author frankiegao123
     * 2010-11-18 ����11:27:04
     */
    int getHitCount();

    /**
     * <p>����һ�λ���������</p>
     *
     * @return
     * @author frankiegao123
     * 2010-11-18 ����11:27:30
     */
    int incrementHitCount();

    /**
     * <p>������뻺��������ʱ��</p>
     *
     * @return
     * @author frankiegao123
     * 2010-11-18 ����11:28:12
     */
    long getCreateTime();
}
