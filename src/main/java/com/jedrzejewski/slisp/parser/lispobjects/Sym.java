package com.jedrzejewski.slisp.parser.lispobjects;

public class Sym implements LispObject {

    private String name;

    public Sym(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sym sym = (Sym) o;

        if (!name.equals(sym.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
