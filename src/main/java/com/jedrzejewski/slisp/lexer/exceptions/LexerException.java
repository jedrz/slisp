package com.jedrzejewski.slisp.lexer.exceptions;

import com.jedrzejewski.slisp.BaseException;

public abstract class LexerException extends BaseException {

    public LexerException() {
        this(null);
    }

    public LexerException(String message) {
        super("lexer", message);
    }
}
