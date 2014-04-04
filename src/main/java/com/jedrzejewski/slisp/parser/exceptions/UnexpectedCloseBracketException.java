package com.jedrzejewski.slisp.parser.exceptions;

public class UnexpectedCloseBracketException extends ParserException {

    public UnexpectedCloseBracketException() {
        super("Unexpected ]");
    }
}
