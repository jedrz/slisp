package com.jedrzejewski.slisp.parser;

import com.jedrzejewski.slisp.BaseException;
import com.jedrzejewski.slisp.lexer.Lexer;
import com.jedrzejewski.slisp.lexer.Token;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Lst;
import com.jedrzejewski.slisp.lispobjects.Num;
import com.jedrzejewski.slisp.lispobjects.Str;
import com.jedrzejewski.slisp.lispobjects.Sym;
import com.jedrzejewski.slisp.lispobjects.Vec;
import com.jedrzejewski.slisp.parser.exceptions.MissingCloseBracketException;
import com.jedrzejewski.slisp.parser.exceptions.MissingCloseParenException;
import com.jedrzejewski.slisp.parser.exceptions.UnexpectedCloseBracketException;
import com.jedrzejewski.slisp.parser.exceptions.UnexpectedCloseParenException;
import com.jedrzejewski.slisp.parser.exceptions.UnexpectedEOFException;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    private Lexer lexer;
    private Map<Token.Type, TokenParser> tokenTypeMethodMap;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        tokenTypeMethodMap = new HashMap<>();
        tokenTypeMethodMap.put(Token.Type.OPEN_PAREN, this::parseOpenParen);
        tokenTypeMethodMap.put(Token.Type.CLOSE_PAREN, this::parseCloseParen);
        tokenTypeMethodMap.put(Token.Type.OPEN_BRACKET, this::parseOpenBracket);
        tokenTypeMethodMap.put(Token.Type.CLOSE_BRACKET, this::parseCloseBracket);
        tokenTypeMethodMap.put(Token.Type.SYMBOL, this::parseSymbol);
        tokenTypeMethodMap.put(Token.Type.NUMBER, this::parseNumber);
        tokenTypeMethodMap.put(Token.Type.STRING, this::parseString);
        tokenTypeMethodMap.put(Token.Type.QUOTE, this::parseQuote);
    }

    public LispObject parse() throws BaseException {
        Token token = lexer.getNextToken();
        if (token == null) {
            return null;
        } else {
            return parseToken(token);
        }
    }

    private LispObject parseToken(Token token) throws BaseException {
        if (token == null) {
            throw new UnexpectedEOFException();
        }
        TokenParser tokenParser = tokenTypeMethodMap.get(token.getType());
        if (tokenParser != null) {
            return tokenParser.parse(token);
        } else {
            throw new RuntimeException("Unhandled token!");
        }
    }

    private LispObject parseQuote(Token token) throws BaseException {
        Lst quote = new Lst();
        quote.add(new Sym("quote"));
        quote.add(parseToken(lexer.getNextToken()));
        return quote;
    }

    private LispObject parseOpenParen(Token token) throws BaseException {
        Lst lst = new Lst();
        while (true) {
            token = lexer.getNextToken();
            if (token == null) {
                throw new MissingCloseParenException();
            } else if (token.getType() == Token.Type.CLOSE_PAREN) {
                return lst;
            } else {
                lst.add(parseToken(token));
            }
        }
    }

    private LispObject parseCloseParen(Token token) throws BaseException {
        throw new UnexpectedCloseParenException();
    }

    private LispObject parseOpenBracket(Token token) throws BaseException {
        Vec vec = new Vec();
        while (true) {
            token = lexer.getNextToken();
            if (token == null) {
                throw new MissingCloseBracketException();
            } else if (token.getType() == Token.Type.CLOSE_BRACKET) {
                return vec;
            } else {
                vec.add(parseToken(token));
            }
        }
    }

    private LispObject parseCloseBracket(Token token) throws BaseException {
        throw new UnexpectedCloseBracketException();
    }

    private LispObject parseSymbol(Token token) {
        return new Sym(token.getString());
    }

    private LispObject parseNumber(Token token) {
        return new Num(token.getString());
    }

    private LispObject parseString(Token token) {
        return new Str(token.getString());
    }

    private interface TokenParser {
        LispObject parse(Token token) throws BaseException;
    }
}
