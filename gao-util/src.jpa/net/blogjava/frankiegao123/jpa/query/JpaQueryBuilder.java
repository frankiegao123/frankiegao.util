package net.blogjava.frankiegao123.jpa.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.blogjava.frankiegao123.log.slf4j.Log;
import net.blogjava.frankiegao123.log.slf4j.LogFactory;
import net.blogjava.frankiegao123.util.Tools;

/**
 * <p>JPA ��������ҳ��ѯ</p>
 *
 * @author frankiegao123
 * 2010-6-20 ����09:29:44
 */
public class JpaQueryBuilder {

    /**
     * ���ڹ��� JPQL ��ѯ���ı����
     */
    public final static String ENTITY_ALIAS = "t";

    /**
     * ���� LIKE ��ѯʱ���ַ�ת��
     */
    public final static char ESCAPE_CHAR = '\\';

    private static Log log = LogFactory.getLog("JpaQueryBuilder-SQL");

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List doQuery(Object param, Paging paging) throws Exception {
        return doQuery(param.getClass(), param, paging);
    }

    /**
     * 
     * <p>ִ�в�ѯ</p>
     *
     * @param <T>       ��ѯ��ʵ������
     * @param entity    ���ڽ綨��ѯ��ʵ������
     * @param param     ��ѯ����
     * @param paging    ��ѯ��ҳ���������
     * @return
     * @throws Exception
     */
    public <T> List<T> doQuery(Class<T> entity, Object param, Paging paging) throws Exception {
        if(entity == null) {
            throw new NullPointerException("entity class cannot null");
        }
        if(param == null) {
            throw new NullPointerException("query parameter cannot null");
        }
        if(!entity.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException(entity.getName() + " isnot a entity");
        }
        StringBuilder sb = buildBaseStatement(entity);
        Map<Integer, Object> values = new HashMap<Integer, Object>();
        buildConditionStatement(sb, values, param);
        buildOrder(sb, paging);

        if(log.isDebugEnabled()) {
            log.debug("{}", sb);
            for(Map.Entry<Integer, Object> entry : values.entrySet()) {
                log.debug("query param: ?{}. {}", entry.getKey(), entry.getValue());
            }
        }

        return executeQuery(entity, sb.toString(), values, paging);
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> executeQuery(Class<T> entity, String jpql, Map<Integer, Object> values, Paging paging) {
        Query query = entityManager.createQuery(jpql);
        for(Map.Entry<Integer, Object> entry : values.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        query.setFirstResult(paging.getFirstResult());
        query.setMaxResults(paging.getPageSize());
        return query.getResultList();
    }

    public static int getCacheSize() {
        return JpaQueryParser.getParser().getCacheSize();
    }

    /**
     * <p>���������� JPQL �е� SELECT...FROM... �Ӿ�</p>
     *
     * @param entity
     * @return
     */
    private StringBuilder buildBaseStatement(Class<?> entity) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ").append(ENTITY_ALIAS);
        sb.append(" FROM ").append(entity.getSimpleName()).append(' ').append(ENTITY_ALIAS);
        return sb;
    }

    /**
     * <p>���� JPQL �е� ��ѯ���� WHERE �Ӿ�</p>
     *
     * @param sb
     * @param values
     * @param param
     * @return
     * @throws Exception
     */
    private StringBuilder buildConditionStatement(StringBuilder sb, Map<Integer, Object> values,
            Object param) throws Exception {
        JpaQueryMeta[] metas = JpaQueryParser.getParser().getQueryMetas(param.getClass());
        
        // �Ƿ���Ҫ���ת���Ӿ�ı�־
        boolean hasLike = false;
        for(int i = 0, k = 1; i < metas.length; i++) {
            Object value = metas[i].getValue(param);
            if(value == null) {
                continue;
            }
            sb.append(k > 1 ? " AND" : " WHERE");
            addValue(values, k, value, metas[i]);
            metas[i].buildQuery(sb, k++);
            if(!hasLike)
                hasLike = metas[i].isLike();
        }
        if(hasLike) {
            sb.append(" ESCAPE '").append(ESCAPE_CHAR).append('\'');
        }
        return sb;
    }

    /**
     * <p>���� JPQL �е� ORDER BY �����Ӿ�</p>
     *
     * @param sb
     * @param paging
     * @return
     */
    private StringBuilder buildOrder(StringBuilder sb, Paging paging) {
        if(paging == null || Tools.isBlank(paging.getOrderColumn())) {
            return sb;
        }
        sb.append(" ORDER BY ");
        String orderColumn = paging.getOrderColumn();
        int idx = orderColumn.indexOf('.');
        if(idx > -1) {
            orderColumn = orderColumn.substring(idx + 1);
        }
        sb.append(ENTITY_ALIAS).append('.');
        sb.append(orderColumn).append(' ');
        sb.append(paging.getOrderDirection());
        return sb;
    }

    private void addValue(Map<Integer, Object> values, int index, Object value,
            JpaQueryMeta meta) {
        values.put(index, meta.buildQueryValue(value));
    }
}