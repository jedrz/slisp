package com.jedrzejewski.slisp.lexer;

public class Token {

    public enum Type {
        OPEN_BRRACKET,
        CLOSE_BRACKET,
        SYMBOL,
        NUMBER,
    }

    private Type type;
    private String string;

    private Token(Type type, String string) {
        this.type = type;
        this.string = string;
    }

    public Type getType() {
        return type;
    }

    public String getString() {
        return string;
    }

    public static Token createOpenBracketToken() {
        return new Token(Type.OPEN_BRRACKET, "(");
    }

    public static Token createCloseBracketToken() {
        return new Token(Type.CLOSE_BRACKET, ")");
    }

    public static Token createSymbolToken(String name) {
        return new Token(Type.SYMBOL, name);
    }

    public static Token createNumberToken(String number) {
        return new Token(Type.NUMBER, number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        if (!string.equals(token.string)) return false;
        if (type != token.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + string.hashCode();
        return result;
    }
}