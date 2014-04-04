package com.jedrzejewski.slisp.parser.exceptions;

public class UnexpectedCloseParenException extends ParserException {

    public UnexpectedCloseParenException() {
        super("Unexcepted )");
    }
}
