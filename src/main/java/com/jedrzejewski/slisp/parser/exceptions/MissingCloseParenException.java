package com.jedrzejewski.slisp.parser.exceptions;

public class MissingCloseParenException extends ParserException {

    public MissingCloseParenException() {
        super("Missing )");
    }
}
