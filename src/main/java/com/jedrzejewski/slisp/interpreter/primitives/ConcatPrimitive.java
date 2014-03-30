package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Lst;
import java.util.List;

public class ConcatPrimitive implements Primitive {

    @Override
    public LispObject call(List<LispObject> args) {
        Lst concatResult = new Lst();
        // TODO: args have to be lists.
        args.forEach(lst -> concatResult.addAll((Lst) lst));
        return concatResult;
    }
}
