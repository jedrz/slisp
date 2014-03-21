package com.jedrzejewski.slisp.parser;

import com.jedrzejewski.slisp.lexer.CloseBracket;
import com.jedrzejewski.slisp.lexer.Lexer;
import com.jedrzejewski.slisp.lexer.OpenBracket;
import com.jedrzejewski.slisp.lexer.Token;
import java.util.LinkedList;
import java.util.List;

public class Parser {

    private Lexer lexer;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public Object parse() {
        Token token = lexer.getNextToken();
        if (token == null) {
            return null;
        } else {
            return parseToken(token);
        }
    }

    private Object parseToken(Token token) {
        if (token instanceof OpenBracket) {
            List<Object> exp = createList();
            while (true) {
                token = lexer.getNextToken();
                if (token instanceof CloseBracket) {
                    return exp;
                } else {
                    exp.add(parseToken(token));
                }
            }
        } else if (token instanceof CloseBracket) {
            return null; // blad
        } else {
            return token;
        }
    }

    private static List<Object> createList() {
        return new LinkedList<>();
    }
}
