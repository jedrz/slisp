package com.jedrzejewski.slisp.lexer;

public class Number extends Token {

    private int number;

    public Number(int number) {
        this.number = number;
    }

    public int get() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Number number1 = (Number) o;

        if (number != number1.number) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return number;
    }
}
