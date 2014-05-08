package com.jedrzejewski.slisp.interpreter.exceptions;

public abstract class ExactFunctionException extends InterpreterException {

    protected String funcName = null;

    public ExactFunctionException() {
        this(null);
    }

    public ExactFunctionException(String message) {
        super(message);
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    @Override
    public String getMessage() {
        // TODO: consider overrinding prefix.
        if (funcName != null) {
            return funcName + ": " + super.getMessage();
        } else {
            return super.getMessage();
        }
    }
}
