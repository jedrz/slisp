package com.jedrzejewski.slisp.lispobjects;

import com.jedrzejewski.slisp.interpreter.Scope;

public class Num implements LispObject {

    private double value;

    public Num(double value) {
        this.value = value;
    }

    public Num(String value) {
        this(Double.valueOf(value));
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Num number = (Num) o;

        if (Double.compare(number.value, value) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(value);
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public LispObject eval(Scope scope) {
        return this;
    }
}
