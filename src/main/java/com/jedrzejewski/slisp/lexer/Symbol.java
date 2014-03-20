package com.jedrzejewski.slisp.lexer;

public class Symbol extends Token {

    private String name;

    public Symbol(String name) {
        this.name = name;
    }


    public String get() {
        return name;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Symbol symbol = (Symbol) o;

        if (!name.equals(symbol.name)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
