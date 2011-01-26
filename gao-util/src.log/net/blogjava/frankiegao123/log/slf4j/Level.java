package net.blogjava.frankiegao123.log.slf4j;

public enum Level {

    DEBUG {
        boolean isEnabled(Log log) {
            return log.isDebugEnabled();
        }

        void log(Log log, String message) {
            log.debug(message);
        }

        void log(Log log, String message, Throwable e) {
            log.debug(message, e);
        }

        void log(Log log, String message, Object arg0) {
            log.debug(message, arg0);
        }

        void log(Log log, String message, Object arg0, Object arg1) {
            log.debug(message, arg0, arg1);
        }

        void log(Log log, String message, Object... args) {
            log.debug(message, args);
        }

        void log(Log log, String message, Throwable e, Object arg0) {
            log.debug(message, e, arg0);
        }

        void log(Log log, String message, Throwable e, Object arg0, Object arg1) {
            log.debug(message, e, arg0, arg1);
        }

        void log(Log log, String message, Throwable e, Object... args) {
            log.debug(message, e, args);
        }   
    },

    INFO {
        boolean isEnabled(Log log) {
            return log.isInfoEnabled();
        }

        void log(Log log, String message) {
            log.info(message);
        }

        void log(Log log, String message, Throwable e) {
            log.info(message, e);
        }

        void log(Log log, String message, Object arg0) {
            log.info(message, arg0);
        }

        void log(Log log, String message, Object arg0, Object arg1) {
            log.info(message, arg0, arg1);
        }

        void log(Log log, String message, Object... args) {
            log.info(message, args);
        }

        void log(Log log, String message, Throwable e, Object arg0) {
            log.info(message, e, arg0);
        }

        void log(Log log, String message, Throwable e, Object arg0, Object arg1) {
            log.info(message, e, arg0, arg1);
        }

        void log(Log log, String message, Throwable e, Object... args) {
            log.info(message, e, args);
        }
    },

    WARN {
        boolean isEnabled(Log log) {
            return log.isWarnEnabled();
        }

        void log(Log log, String message) {
            log.warn(message);
        }

        void log(Log log, String message, Throwable e) {
            log.warn(message, e);
        }

        void log(Log log, String message, Object arg0) {
            log.warn(message, arg0);
        }

        void log(Log log, String message, Object arg0, Object arg1) {
            log.warn(message, arg0, arg1);
        }

        void log(Log log, String message, Object... args) {
            log.warn(message, args);
        }

        void log(Log log, String message, Throwable e, Object arg0) {
            log.warn(message, e, arg0);
        }

        void log(Log log, String message, Throwable e, Object arg0, Object arg1) {
            log.warn(message, e, arg0, arg1);
        }

        void log(Log log, String message, Throwable e, Object... args) {
            log.warn(message, e, args);
        }
    },

    ERROR {
        boolean isEnabled(Log log) {
            return log.isErrorEnabled();
        }

        void log(Log log, String message) {
            log.error(message);
        }

        void log(Log log, String message, Throwable e) {
            log.error(message, e);
        }

        void log(Log log, String message, Object arg0) {
            log.error(message, arg0);
        }

        void log(Log log, String message, Object arg0, Object arg1) {
            log.error(message, arg0, arg1);
        }

        void log(Log log, String message, Object... args) {
            log.error(message, args);
        }

        void log(Log log, String message, Throwable e, Object arg0) {
            log.error(message, e, arg0);
        }

        void log(Log log, String message, Throwable e, Object arg0, Object arg1) {
            log.error(message, e, arg0, arg1);
        }

        void log(Log log, String message, Throwable e, Object... args) {
            log.error(message, e, args);
        }
    }
    ;


    // 定义几个抽象方法，用于多态的实现
    
    /**
     * 是否启用当前级别的日志
     */
    abstract boolean isEnabled(Log log);
    
    /**
     * 记录当前级别的日志
     * @param log
     * @param message
     * @param args
     */
    abstract void log(Log log, String message);
    abstract void log(Log log, String message, Throwable e);
    abstract void log(Log log, String message, Object arg0);
    abstract void log(Log log, String message, Object arg0, Object arg1);
    abstract void log(Log log, String message, Object... args);
    abstract void log(Log log, String message, Throwable e, Object arg0);
    abstract void log(Log log, String message, Throwable e, Object arg0, Object arg1);
    abstract void log(Log log, String message, Throwable e, Object... args);
}
