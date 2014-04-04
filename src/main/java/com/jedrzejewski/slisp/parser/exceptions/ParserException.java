package com.jedrzejewski.slisp.parser.exceptions;

import com.jedrzejewski.slisp.BaseException;

public abstract class ParserException extends BaseException {

    public ParserException() {
        this(null);
    }

    public ParserException(String message) {
        super("parser", message);
    }
}
