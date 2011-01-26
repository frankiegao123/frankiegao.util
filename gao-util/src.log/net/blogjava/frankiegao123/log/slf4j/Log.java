package net.blogjava.frankiegao123.log.slf4j;

public interface Log {
    
    public boolean isDebugEnabled();
    public boolean isInfoEnabled();
    public boolean isWarnEnabled();
    public boolean isErrorEnabled();
    public boolean isEnabled(Level level);
    
    public void debug(String message);
    public void debug(String message, Throwable e);
    public void debug(String message, Object arg0);
    public void debug(String message, Object arg0, Object arg1);
    public void debug(String message, Object... args);
    public void debug(String message, Throwable e, Object arg0);
    public void debug(String message, Throwable e, Object arg0, Object arg1);
    public void debug(String message, Throwable e, Object... args);
    
    public void info(String message);
    public void info(String message, Throwable e);
    public void info(String message, Object arg0);
    public void info(String message, Object arg0, Object arg1);
    public void info(String message, Object... args);
    public void info(String message, Throwable e, Object arg0);
    public void info(String message, Throwable e, Object arg0, Object arg1);
    public void info(String message, Throwable e, Object... args);

    public void warn(String message);
    public void warn(String message, Throwable e);
    public void warn(String message, Object arg0);
    public void warn(String message, Object arg0, Object arg1);
    public void warn(String message, Object... args);
    public void warn(String message, Throwable e, Object arg0);
    public void warn(String message, Throwable e, Object arg0, Object arg1);
    public void warn(String message, Throwable e, Object... args);

    public void error(String message);
    public void error(String message, Throwable e);
    public void error(String message, Object arg0);
    public void error(String message, Object arg0, Object arg1);
    public void error(String message, Object... args);
    public void error(String message, Throwable e, Object arg0);
    public void error(String message, Throwable e, Object arg0, Object arg1);
    public void error(String message, Throwable e, Object... args);
    
    public void log(Level level, String message);
    public void log(Level level, String message, Throwable e);
    public void log(Level level, String message, Object arg0);
    public void log(Level level, String message, Object arg0, Object arg1);
    public void log(Level level, String message, Object... args);
    public void log(Level level, String message, Throwable e, Object arg0);
    public void log(Level level, String message, Throwable e, Object arg0, Object arg1);
    public void log(Level level, String message, Throwable e, Object... args);
}
