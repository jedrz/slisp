package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.Callable;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import java.util.List;

public class CallablePredicatePrimitive extends Primitive {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope) {
        return new Bool(args.get(0) instanceof Callable);
    }
}
