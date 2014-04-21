package com.jedrzejewski.slisp.lispobjects;

import com.jedrzejewski.slisp.interpreter.Scope;

public class Str implements LispObject {

    private String string;

    public Str(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    @Override
    public String toString() {
        return "\"" + string.toString() + "\"";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Str)) {
            return false;
        }

        Str str = (Str) o;

        if (!string.equals(str.string)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return string.hashCode();
    }

    @Override
    public LispObject eval(Scope scope) {
        return this;
    }
}
