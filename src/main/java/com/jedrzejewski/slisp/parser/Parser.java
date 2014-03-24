package com.jedrzejewski.slisp.parser;

import com.jedrzejewski.slisp.lexer.Lexer;
import com.jedrzejewski.slisp.lexer.Token;
import com.jedrzejewski.slisp.parser.lispobjects.LispObject;
import com.jedrzejewski.slisp.parser.lispobjects.Lst;
import com.jedrzejewski.slisp.parser.lispobjects.Num;
import com.jedrzejewski.slisp.parser.lispobjects.Sym;

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
        if (token.getType() == Token.Type.OPEN_PAREN) {
            Lst lst = new Lst();
            while (true) {
                token = lexer.getNextToken();
                if (token.getType() == Token.Type.CLOSE_PAREN) {
                    return lst;
                } else {
                    lst.add(parseToken(token));
                }
            }
        } else if (token.getType() == Token.Type.CLOSE_PAREN) {
            return null; // blad
        } else if (token.getType() == Token.Type.SYMBOL) {
            return new Sym(token.getString());
        } else if (token.getType() == Token.Type.NUMBER) {
            return new Num(token.getString());
        }
        return null;
    }
}
