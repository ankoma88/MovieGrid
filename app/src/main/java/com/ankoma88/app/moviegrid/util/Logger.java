package com.ankoma88.app.moviegrid.util;


import java.io.PrintWriter;
import java.io.StringWriter;

public class Logger {

    public static enum LogLevel {
        ALL,
        VERBOSE,
        DEBUG,
        INFO,
        WARN,
        ERR,
        NONE
    }

    private static LogLevel sLogLevel = LogLevel.INFO;

    public static LogLevel getLogLevel() {
        return sLogLevel;
    }

    public static void setLogLevel(LogLevel logLevel) {
        Logger.sLogLevel = logLevel;
    }

    private static boolean isLogEnabled(LogLevel level) {
        return sLogLevel.ordinal() <= level.ordinal();
    }

    public static String getTag(Class<?> classObj) {
        return classObj.getName();
    }

    public static String getTag(Object obj) {
        return obj == null ? "null" : obj.getClass().getName();
    }

    private static String arrayToString(Object... arr) {
        StringBuilder builder = new StringBuilder();
        for (Object obj : arr) {
            if (obj == null) {
                builder.append("null");
            } else if (obj instanceof Throwable) {
                builder.append(android.util.Log.getStackTraceString((Throwable) obj));
            } else {
                builder.append(obj.toString());
            }
            builder.append(" ");
        }
        return builder.toString();
    }

    public static void logVerbose(String tag, String message) {
        if (isLogEnabled(LogLevel.VERBOSE) && message != null) {
            android.util.Log.v(tag, message);
        }
    }

    public static void logVerbose(String tag, Throwable e) {
        if (e.getMessage() == null) {
            if (isLogEnabled(LogLevel.VERBOSE)) {
                e.printStackTrace();
            }
        } else {
            logVerbose(tag, e.getMessage());
        }
    }

    public static void logVerbose(String tag, Object... message) {
        if (isLogEnabled(LogLevel.VERBOSE) && message != null) {
            android.util.Log.v(tag, arrayToString(message));
        }
    }

    public static void logDebug(String tag, String message) {
        if (isLogEnabled(LogLevel.DEBUG) && message != null) {
            android.util.Log.d(tag, message);
        }
    }

    public static void logDebug(String tag, Throwable e) {
        if (e.getMessage() == null) {
            if (isLogEnabled(LogLevel.DEBUG)) {
                e.printStackTrace();
            }
        } else {
            logDebug(tag, e.getMessage());
        }
    }

    public static void logDebug(String tag, Object... message) {
        if (isLogEnabled(LogLevel.DEBUG) && message != null) {
            android.util.Log.d(tag, arrayToString(message));
        }
    }

    public static void logInfo(String tag, String message) {
        if (isLogEnabled(LogLevel.INFO) && message != null) {
            android.util.Log.i(tag, message);
        }
    }

    public static void logInfo(String tag, Throwable e) {
        if (e.getMessage() == null) {
            if (isLogEnabled(LogLevel.INFO)) {
                e.printStackTrace();
            }
        } else {
            logInfo(tag, e.getMessage());
        }
    }

    public static void logInfo(String tag, Object... message) {
        if (isLogEnabled(LogLevel.INFO) && message != null) {
            android.util.Log.i(tag, arrayToString(message));
        }
    }

    public static void logWarn(String tag, String message) {
        if (isLogEnabled(LogLevel.WARN) && message != null) {
            android.util.Log.w(tag, message);
        }
    }

    public static void logWarn(String tag, Throwable e) {
        if (e.getMessage() == null) {
            if (isLogEnabled(LogLevel.WARN)) {
                e.printStackTrace();
            }
        } else {
            logWarn(tag, e.getMessage());
        }
    }

    public static void logWarn(String tag, Object... message) {
        if (isLogEnabled(LogLevel.WARN) && message != null) {
            android.util.Log.w(tag, arrayToString(message));
        }
    }

    public static void logError(String tag, String message) {
        if (isLogEnabled(LogLevel.ERR) && message != null) {
            android.util.Log.e(tag, message);
        }
    }

    public static void logError(String tag, Throwable e) {
        if (e == null) {
            return;
        }

        if (e.getMessage() == null) {
            if (isLogEnabled(LogLevel.ERR)) {
                e.printStackTrace();
            }
        } else {
            logError(tag, e.getMessage());
        }
    }

    public static void logError(String tag, Object... message) {
        if (isLogEnabled(LogLevel.ERR) && message != null) {
            android.util.Log.e(tag, arrayToString(message));
        }
    }

    public static String stackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    public static void log(String tag, Object... message) {
        logError(tag, message);
    }

}

