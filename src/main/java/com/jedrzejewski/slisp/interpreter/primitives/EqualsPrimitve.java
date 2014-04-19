package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import java.util.List;

public class EqualsPrimitve extends Primitive {

    @Override
    public LispObject call(List<LispObject> args) {
        LispObject prev = args.get(0);
        for (LispObject arg : args.subList(1, args.size())) {
            if (!prev.equals(arg)) {
                return new Bool(false);
            } else {
                prev = arg;
            }
        }
        return new Bool(true);
    }
}
