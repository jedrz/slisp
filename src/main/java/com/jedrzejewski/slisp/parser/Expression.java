package com.jedrzejewski.slisp.parser;

import com.jedrzejewski.slisp.lexer.Token;
import java.util.LinkedList;
import java.util.List;

public class Expression extends Token {

    private List<Token> expression;

    public Expression() {
        expression = new LinkedList<>();
    }

    public boolean add(Token token) {
        return expression.add(token);
    }

    public Token get(int index) {
        return expression.get(index);
    }
}
