package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import java.util.List;

public class EqualsPrimitive extends Primitive {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope) {
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
