package com.jedrzejewski.slisp.parser;

import com.jedrzejewski.slisp.lexer.CloseBracket;
import com.jedrzejewski.slisp.lexer.Lexer;
import com.jedrzejewski.slisp.lexer.OpenBracket;
import com.jedrzejewski.slisp.lexer.Token;

public class Parser {

    private Lexer lexer;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public Token parse() {
        Token token = lexer.getNextToken();
        if (token == null) {
            return null;
        } else {
            return parseToken(token);
        }
    }

    private Token parseToken(Token token) {
        if (token instanceof OpenBracket) {
            Expression exp = new Expression();
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
}
