package com.jedrzejewski.slisp.parser.exceptions;

public class MissingCloseBracketException extends ParserException {

    public MissingCloseBracketException() {
        super("Missing ]");
    }
}
