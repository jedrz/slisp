package com.jedrzejewski.slisp.parser;

public class Number implements LispObject {

    private double value;

    public Number(double value) {
        this.value = value;
    }

    public Number(String value) {
        this(Double.valueOf(value));
    }

    public Double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Number number = (Number) o;

        if (Double.compare(number.value, value) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(value);
        return (int) (temp ^ (temp >>> 32));
    }
}
