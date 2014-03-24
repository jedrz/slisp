package com.jedrzejewski.slisp.parser;

import com.jedrzejewski.slisp.lexer.Lexer;
import com.jedrzejewski.slisp.lexer.Token;
import com.jedrzejewski.slisp.parser.lispobjects.LispObject;
import com.jedrzejewski.slisp.parser.lispobjects.Lst;
import com.jedrzejewski.slisp.parser.lispobjects.Num;
import com.jedrzejewski.slisp.parser.lispobjects.Sym;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Parser {

    private Lexer lexer;
    private Map<Token.Type, Function<Token, LispObject>> tokenTypeMethodMap;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        tokenTypeMethodMap = new HashMap<>();
        tokenTypeMethodMap.put(Token.Type.OPEN_PAREN, this::parseOpenParen);
        tokenTypeMethodMap.put(Token.Type.CLOSE_PAREN, this::parseCloseParen);
        tokenTypeMethodMap.put(Token.Type.SYMBOL, this::parseSymbol);
        tokenTypeMethodMap.put(Token.Type.NUMBER, this::parseNumber);
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
        Function<Token, LispObject> method
                = tokenTypeMethodMap.get(token.getType());
        if (method != null) {
            return method.apply(token);
        } else {
            throw new RuntimeException("Unhandled token!");
        }
    }

    private LispObject parseOpenParen(Token token) {
        Lst lst = new Lst();
        while (true) {
            token = lexer.getNextToken();
            if (token.getType() == Token.Type.CLOSE_PAREN) {
                return lst;
            } else {
                lst.add(parseToken(token));
            }
        }
    }

    private LispObject parseCloseParen(Token token) {
        return null; // TODO: rzuć wyjątek
    }

    private LispObject parseSymbol(Token token) {
        return new Sym(token.getString());
    }

    private LispObject parseNumber(Token token) {
        return new Num(token.getString());
    }
}
