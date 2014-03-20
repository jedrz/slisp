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
        return parse(null);
    }

    private Token parse(Expression expression) {
        Token token = lexer.getNextToken();
        if (token instanceof OpenBracket) {
            if (expression != null) {
                expression.add(parse(new Expression()));
                return expression;
            } else {
                return parse(new Expression());
            }

        } else if (token instanceof CloseBracket) {
            return parse(expression);
        } else if (token == null) {
            return expression;
        } else {
            expression.add(token);
            return parse(expression);
        }
    }
}
