package net.blogjava.frankiegao123.log.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;

class LogImpl implements Log {
    
    private Logger log;
    
    LogImpl(String name) {
        log = LoggerFactory.getLogger(name);
    }
    
    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    public boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    public boolean isWarnEnabled() {
        return log.isWarnEnabled();
    }

    public boolean isErrorEnabled() {
        return log.isErrorEnabled();
    }
    
    public boolean isEnabled(Level level) {
        return level.isEnabled(this);
    }

    public void debug(String message) {
        if(log.isDebugEnabled())
            log.debug(message);
    }
    
    public void debug(String message, Throwable e) {
        if(log.isDebugEnabled())
            log.debug(message, e);
    }

    public void debug(String message, Object arg0) {
        if(log.isDebugEnabled())
            log.debug(message, arg0);
    }

    public void debug(String message, Object arg0, Object arg1) {
        if(log.isDebugEnabled())
            log.debug(message, arg0, arg1);
    }

    public void debug(String message, Object... args) {
        if(log.isDebugEnabled())
            log.debug(message, args);
    }

    public void debug(String message, Throwable e, Object arg0) {
        if(log.isDebugEnabled())
            log.debug(MessageFormatter.format(message, arg0).getMessage(), e);
    }

    public void debug(String message, Throwable e, Object arg0, Object arg1) {
        if(log.isDebugEnabled())
            log.debug(MessageFormatter.format(message, arg0, arg1).getMessage(), e);
    }

    public void debug(String message, Throwable e, Object... args) {
        if(log.isDebugEnabled())
            log.debug(MessageFormatter.arrayFormat(message, args).getMessage(), e);
    }
    
    public void info(String message) {
        if(log.isInfoEnabled())
            log.info(message);
    }
    
    public void info(String message, Throwable e) {
        if(log.isInfoEnabled())
            log.info(message, e);
    }

    public void info(String message, Object arg0) {
        if(log.isInfoEnabled())
            log.info(message, arg0);
    }

    public void info(String message, Object arg0, Object arg1) {
        if(log.isInfoEnabled())
            log.info(message, arg0, arg1);
    }

    public void info(String message, Object... args) {
        if(log.isInfoEnabled())
            log.info(message, args);
    }

    public void info(String message, Throwable e, Object arg0) {
        if(log.isInfoEnabled())
            log.info(MessageFormatter.format(message, arg0).getMessage(), e);
    }

    public void info(String message, Throwable e, Object arg0, Object arg1) {
        if(log.isInfoEnabled())
            log.info(MessageFormatter.format(message, arg0, arg1).getMessage(), e);
    }

    public void info(String message, Throwable e, Object... args) {
        if(log.isInfoEnabled())
            log.info(MessageFormatter.arrayFormat(message, args).getMessage(), e);
    }
    
    public void warn(String message) {
        if(log.isWarnEnabled())
            log.warn(message);
    }
    
    public void warn(String message, Throwable e) {
        if(log.isWarnEnabled())
            log.warn(message, e);
    }

    public void warn(String message, Object arg0) {
        if(log.isWarnEnabled())
            log.warn(message, arg0);
    }

    public void warn(String message, Object arg0, Object arg1) {
        if(log.isWarnEnabled())
            log.warn(message, arg0, arg1);
    }

    public void warn(String message, Object... args) {
        if(log.isWarnEnabled())
            log.warn(message, args);
    }

    public void warn(String message, Throwable e, Object arg0) {
        if(log.isWarnEnabled())
            log.warn(MessageFormatter.format(message, arg0).getMessage(), e);
    }

    public void warn(String message, Throwable e, Object arg0, Object arg1) {
        if(log.isWarnEnabled())
            log.warn(MessageFormatter.format(message, arg0, arg1).getMessage(), e);
    }

    public void warn(String message, Throwable e, Object... args) {
        if(log.isWarnEnabled())
            log.warn(MessageFormatter.arrayFormat(message, args).getMessage(), e);
    }
    
    public void error(String message) {
        if(log.isErrorEnabled())
            log.error(message);
    }
    
    public void error(String message, Throwable e) {
        if(log.isErrorEnabled())
            log.error(message, e);
    }

    public void error(String message, Object arg0) {
        if(log.isErrorEnabled())
            log.error(message, arg0);
    }

    public void error(String message, Object arg0, Object arg1) {
        if(log.isErrorEnabled())
            log.error(message, arg0, arg1);
    }

    public void error(String message, Object... args) {
        if(log.isErrorEnabled())
            log.error(message, args);
    }

    public void error(String message, Throwable e, Object arg0) {
        if(log.isErrorEnabled())
            log.error(MessageFormatter.format(message, arg0).getMessage(), e);
    }

    public void error(String message, Throwable e, Object arg0, Object arg1) {
        if(log.isErrorEnabled())
            log.error(MessageFormatter.format(message, arg0, arg1).getMessage(), e);
    }

    public void error(String message, Throwable e, Object... args) {
        if(log.isErrorEnabled())
            log.error(MessageFormatter.arrayFormat(message, args).getMessage(), e);
    }

    public void log(Level level, String message) {
        level.log(this, message);        
    }

    public void log(Level level, String message, Throwable e) {
        level.log(this, message, e);        
    }

    public void log(Level level, String message, Object arg0) {
        level.log(this, message, arg0);
    }

    public void log(Level level, String message, Object arg0, Object arg1) {
        level.log(this, message, arg0, arg1);        
    }

    public void log(Level level, String message, Object... args) {
        level.log(this, message, args);
    }

    public void log(Level level, String message, Throwable e, Object arg0) {
        level.log(this, message, e, arg0);
    }

    public void log(Level level, String message, Throwable e, Object arg0, Object arg1) {
        level.log(this, message, e, arg0, arg1);
    }

    public void log(Level level, String message, Throwable e, Object... args) {
        level.log(this, message, e, args);
    }
}
