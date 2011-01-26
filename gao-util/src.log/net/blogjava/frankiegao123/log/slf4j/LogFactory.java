package net.blogjava.frankiegao123.log.slf4j;

public class LogFactory {
    
    private LogFactory() {
    }

    public static Log getLog(Class<?> clazz) {
        return getLog(clazz.getName());
    }
    
    public static Log getLog(String name) {
        return new LogImpl(name);
    }
}