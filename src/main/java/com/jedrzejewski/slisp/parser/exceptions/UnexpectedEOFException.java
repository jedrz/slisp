package com.jedrzejewski.slisp.parser.exceptions;

public class UnexpectedEOFException extends ParserException {

    public UnexpectedEOFException() {
        super("Unexpected EOF");
    }

}
