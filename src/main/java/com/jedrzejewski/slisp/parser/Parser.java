package com.jedrzejewski.slisp.parser;

import com.jedrzejewski.slisp.lexer.Lexer;
import com.jedrzejewski.slisp.lexer.Token;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Lst;
import com.jedrzejewski.slisp.lispobjects.Num;
import com.jedrzejewski.slisp.lispobjects.Sym;
import com.jedrzejewski.slisp.lispobjects.Vec;
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
        tokenTypeMethodMap.put(Token.Type.OPEN_BRACKET, this::parseOpenBracket);
        tokenTypeMethodMap.put(Token.Type.CLOSE_BRACKET, this::parseCloseBracket);
        tokenTypeMethodMap.put(Token.Type.SYMBOL, this::parseSymbol);
        tokenTypeMethodMap.put(Token.Type.NUMBER, this::parseNumber);
        tokenTypeMethodMap.put(Token.Type.QUOTE, this::parseQuote);
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

    private LispObject parseQuote(Token token) {
        Lst quote = new Lst();
        quote.add(new Sym("quote"));
        quote.add(parseToken(lexer.getNextToken()));
        return quote;
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

    private LispObject parseOpenBracket(Token token) {
        Vec vec = new Vec();
        while (true) {
            token = lexer.getNextToken();
            if (token.getType() == Token.Type.CLOSE_BRACKET) {
                return vec;
            } else {
                vec.add(parseToken(token));
            }
        }
    }

    private LispObject parseCloseBracket(Token token) {
        return null; // TODO: rzuć wyjątek
    }

    private LispObject parseSymbol(Token token) {
        return new Sym(token.getString());
    }

    private LispObject parseNumber(Token token) {
        return new Num(token.getString());
    }
}
