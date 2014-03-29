package com.jedrzejewski.slisp.lispobjects;

public class Bool implements LispObject {

    private boolean value;

    public Bool(boolean value) {
        this.value = value;
    }

    public Bool(LispObject object) {
        // Only false and nil are false.
        if (object instanceof Bool && ((Bool) object).isFalse()) {
            value = false;
        } else {
            value = true;
        }
    }

    public boolean getValue() {
        return value;
    }

    public boolean isTrue() {
        return getValue();
    }

    public boolean isFalse() {
        return !getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bool bool = (Bool) o;

        if (value != bool.value) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (value ? 1 : 0);
    }
}
