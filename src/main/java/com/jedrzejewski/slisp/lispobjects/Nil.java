package com.jedrzejewski.slisp.lispobjects;

public class Nil extends Bool {

    public Nil() {
        super(false);
    }

    @Override
    public String toString() {
        return "nil";
    }
}
