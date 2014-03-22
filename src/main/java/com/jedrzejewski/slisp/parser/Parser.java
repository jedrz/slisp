package com.jedrzejewski.slisp.parser;

import com.jedrzejewski.slisp.lexer.Lexer;
import com.jedrzejewski.slisp.lexer.Token;
import com.jedrzejewski.slisp.parser.lispobjects.Expression;
import com.jedrzejewski.slisp.parser.lispobjects.LispObject;
import com.jedrzejewski.slisp.parser.lispobjects.Num;
import com.jedrzejewski.slisp.parser.lispobjects.Symbol;

public class Parser {

    private Lexer lexer;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public LispObject parse() {
        Token token = lexer.getNextToken();
        if (token == null) {
            return null;
        } else {
            return parseToken(token);
        }
    }

    private LispObject parseToken(Token token) {
        if (token.getType() == Token.Type.OPEN_BRRACKET) {
            Expression exp = new Expression();
            while (true) {
                token = lexer.getNextToken();
                if (token.getType() == Token.Type.CLOSE_BRACKET) {
                    return exp;
                } else {
                    exp.add(parseToken(token));
                }
            }
        } else if (token.getType() == Token.Type.CLOSE_BRACKET) {
            return null; // blad
        } else if (token.getType() == Token.Type.SYMBOL) {
            return new Symbol(token.getString());
        } else if (token.getType() == Token.Type.NUMBER) {
            return new Num(token.getString());
        }
        return null;
    }
}
