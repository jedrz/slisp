package com.jedrzejewski.slisp.lispobjects;

import com.jedrzejewski.slisp.interpreter.Scope;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Vec extends LinkedList<LispObject> implements LispObject {

    @Override
    public String toString() {
        return "["
               + stream()
                .map(obj -> obj.toString())
                .collect(Collectors.joining(" "))
               + "]";
    }

    @Override
    public LispObject eval(Scope scope) {
        // TODO: eval elements and return such a vector.
        return this;
    }
}
