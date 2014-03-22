package com.jedrzejewski.slisp.lexer;

public class Lexer {

    private String in;
    private int index;
    private boolean isPreviousCh = false;
    private int previousCh = -1;

    public Lexer(String in) {
        this.in = in;
    }

    public Token getNextToken() {
        skipWhitespace();

        int c = getNextCh();

        switch (c) {
            case '(':
                return Token.createOpenBracketToken();
            case ')':
                return Token.createCloseBracketToken();
        }

        if (isNumberCh(c)) {
            return getNumber(c);
        }

        if (isSymbolCh(c)) {
            return getSymbol(c);
        }

        return null;
    }

    private Token getSymbol(int c) {
        String symbol = Character.toString((char) c);
        while ((c = getNextCh()) != -1 && isSymbolCh(c)) {
            symbol += Character.toString((char) c);
        }
        ungetCh(c);
        return Token.createSymbolToken(symbol);
    }

    private boolean isSymbolCh(int c) {
        return Character.isLetterOrDigit(c)
                || c == '?' || c == '!'
                || c == '+' || c == '-' || c == '*' || c == '/';
    }

    private Token getNumber(int c) {
        String number = Character.toString((char) c);
        while ((c = getNextCh()) != -1 && isNumberCh(c)) {
            number += Character.toString((char) c);
        }
        ungetCh(c);
        return Token.createNumberToken(number);
    }

    private boolean isNumberCh(int c) {
        return Character.isDigit(c);
    }

    private int getNextCh() {
        if (isPreviousCh) {
            isPreviousCh = false;
            return previousCh;
        }
        if (index < in.length()) {
            return in.charAt(index++);
        }
        return -1;
    }

    private void ungetCh(int c) {
        previousCh = c;
        isPreviousCh = true;
    }

    private void skipWhitespace() {
        int c;
        while ((c = getNextCh()) != -1 && Character.isWhitespace(c)) {
        }
        ungetCh(c);
    }
}
