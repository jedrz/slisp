package com.jedrzejewski.slisp.interpreter.exceptions;

public class SymbolUndefinedException extends InterpreterException {

    public SymbolUndefinedException(String symbol) {
        super(symbol + " is undefined");
    }
}
