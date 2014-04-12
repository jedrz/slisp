package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Lst;
import java.util.List;

public class ListPrimitive extends Primitive {

    @Override
    public LispObject call(List<LispObject> args) {
        Lst lst = new Lst();
        lst.addAll(args);
        return lst;
    }
}
