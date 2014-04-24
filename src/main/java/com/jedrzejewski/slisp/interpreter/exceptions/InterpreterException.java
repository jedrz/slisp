package com.jedrzejewski.slisp.interpreter.exceptions;

import com.jedrzejewski.slisp.BaseException;

public class InterpreterException extends BaseException {

    public InterpreterException() {
        this(null);
    }

    public InterpreterException(String message) {
        super("interpreter", message);
    }
}
