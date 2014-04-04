package com.jedrzejewski.slisp.lispobjects;

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
}
