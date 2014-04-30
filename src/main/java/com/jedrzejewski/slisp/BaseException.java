package com.jedrzejewski.slisp;

import java.util.Arrays;
import java.util.List;

public abstract class BaseException extends Exception {

    protected String prefix;

    public BaseException() {
        this(null, null);
    }

    public BaseException(String prefix) {
        this(prefix, null);
    }

    public BaseException(String prefix, String message) {
        super(message);
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    @Override
    public String getMessage() {
        if (super.getMessage() == null) {
            return buildMessage(getClass());
        } else {
            return super.getMessage();
        }
    }

    public String getFullMessage() {
        if (getPrefix() == null) {
            return getMessage();
        } else {
            return getPrefix() + ": " + getMessage();
        }
    }

    public static String buildMessage(Class<?> classObj) {
        String className = classObj.getSimpleName();
        if (className == null) {
            return "anonymous class";
        }
        className = className.replaceAll("([A-Z])", " $1").toLowerCase();
        List<String> words = Arrays.asList(className.split(" "));
        String message = String.join(" ", words.subList(0, words.size() - 1));
        return message.trim();
    }
}
