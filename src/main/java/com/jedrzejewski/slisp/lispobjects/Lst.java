package com.jedrzejewski.slisp.lispobjects;

import com.jedrzejewski.slisp.interpreter.Scope;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Lst extends LinkedList<LispObject> implements LispObject {

    @Override
    public String toString() {
        return "("
                + stream()
                .map(obj -> obj.toString())
                .collect(Collectors.joining(" "))
                + ")";
    }

    @Override
    public LispObject eval(Scope scope) {
        LispObject first = get(0);
        List<LispObject> rest = subList(1, size());
        Callable callable = (Callable) first.eval(scope);
        return callable.call(rest, scope);
    }
}
