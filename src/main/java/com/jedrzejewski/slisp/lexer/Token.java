package com.jedrzejewski.slisp.lexer;

public abstract class Token {

    public boolean equals(Object o) {
        return this == o
                || (o == null || getClass() != o.getClass())
                || getClass() == o.getClass();
    }
}