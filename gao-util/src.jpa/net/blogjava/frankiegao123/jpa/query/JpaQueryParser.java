package net.blogjava.frankiegao123.jpa.query;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.blogjava.frankiegao123.jpa.query.annotation.JpaQueries;
import net.blogjava.frankiegao123.jpa.query.annotation.JpaQuery;
import net.blogjava.frankiegao123.jpa.query.annotation.JpaQueryIgnore;
import net.blogjava.frankiegao123.log.slf4j.Log;
import net.blogjava.frankiegao123.log.slf4j.LogFactory;
import net.blogjava.frankiegao123.util.Tools;

/**
 * <p>Parse annotations of JPA query</p>
 *
 * @author frankiegao123
 * 2010-6-30 ÉÏÎç11:23:32
 */
class JpaQueryParser {

    private static JpaQueryParser parser = new JpaQueryParser();

    private static Log log = LogFactory.getLog(JpaQueryParser.class);

    private Map<Class<?>, JpaQueryMeta[]> cache = new ConcurrentHashMap<Class<?>, JpaQueryMeta[]>();

    private JpaQueryParser() {
    }

    static JpaQueryParser getParser() {
        return parser;
    }

    int getCacheSize() {
        return cache.size();
    }

    JpaQueryMeta[] getQueryMetas(Class<?> clazz) throws Exception {
        JpaQueryMeta[] metas = cache.get(clazz);
        if(metas == null) {
            metas = parseJpaQueryMeta(clazz);
            cache.put(clazz, metas);
        }
        return metas;
    }

    private JpaQueryMeta[] parseJpaQueryMeta(Class<?> clazz) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        List<JpaQueryMeta> list = new LinkedList<JpaQueryMeta>();
        for(int i = 0; i < pds.length; i++) {
            Method readMethod = pds[i].getReadMethod();
            Method writeMethod = pds[i].getWriteMethod();
            if(readMethod == null) {
                log.error("class [{}] property [{}] cannot find read method", clazz.getName(), pds[i].getName());
                continue;
            }
            if(ignoreProperty(readMethod, writeMethod)) {
                log.info("class [{}] property [{}] was annotated @JpaQueryIgnore, so ignore the property", clazz.getName(), pds[i].getName());
                continue;
            }
            if(!processJpaQuery(readMethod, writeMethod, pds[i], list)) {
                processJpaQueries(readMethod, writeMethod, pds[i], list);
            }
        }
        JpaQueryMeta[] metas = list.toArray(new JpaQueryMeta[list.size()]);
        Arrays.sort(metas);
        return metas;
    }

    private boolean processJpaQuery(Method readMethod, Method writeMethod, PropertyDescriptor pd,
            List<JpaQueryMeta> list) throws Exception {
        JpaQuery jpaQuery = readMethod.getAnnotation(JpaQuery.class);
        if(jpaQuery == null && writeMethod != null) {
            jpaQuery = writeMethod.getAnnotation(JpaQuery.class);
        }
        if(jpaQuery == null) {
            return false;
        }
        parseJpaQuery(jpaQuery, pd, list);
        return true;
    }

    private void processJpaQueries(Method readMethod, Method writeMethod, PropertyDescriptor pd,
            List<JpaQueryMeta> list) throws Exception {
        JpaQueries queries = readMethod.getAnnotation(JpaQueries.class);
        if(queries == null && writeMethod != null) {
            queries = writeMethod.getAnnotation(JpaQueries.class);
        }
        parseJpaQueries(queries, pd, list);
    }

    private boolean ignoreProperty(Method readMethod, Method writeMethod) {
        if(ignoreMethod(readMethod)) {
            return true;
        }
        if(ignoreMethod(writeMethod)) {
            return true;
        }
        return false;
    }

    private boolean ignoreMethod(Method method) {
        if(method != null && method.isAnnotationPresent(JpaQueryIgnore.class)) {
            return true;
        }
        return false;
    }

    private void parseJpaQueries(JpaQueries queries, PropertyDescriptor pd,
            List<JpaQueryMeta> list) throws Exception {
        if(queries == null) {
            list.add(new JpaQueryMeta(pd, QueryMode.EQ, pd.getName(), 0));
            return;
        }
        JpaQuery[] jpaQueries = queries.value();
        for(int i = 0; i < jpaQueries.length; i++) {
            parseJpaQuery(jpaQueries[i], pd, list);
        }
    }

    private void parseJpaQuery(JpaQuery jpaQuery, PropertyDescriptor pd,
            List<JpaQueryMeta> list) throws Exception {
        String property = jpaQuery.property();
        if(Tools.isBlank(property)) {
            property = pd.getName();
        }

        String valueExpr = pd.getName();
        if(!Tools.isBlank(jpaQuery.objectProperty())) {
            valueExpr += '.' + jpaQuery.objectProperty();
        }
        list.add(new JpaQueryMeta(pd, jpaQuery.mode(), property, valueExpr, jpaQuery.index()));
    }
}
