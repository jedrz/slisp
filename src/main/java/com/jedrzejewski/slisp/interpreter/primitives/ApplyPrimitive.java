package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.lispobjects.Callable;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Lst;
import java.util.List;

public class ApplyPrimitive extends Primitive {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope) {
        Callable fn = (Callable) args.get(0);
        Lst flattenedArgs = new Lst();
        for (LispObject arg : args.subList(1, args.size())) {
            if (arg instanceof Lst) {
                Lst lst = (Lst) arg;
                flattenedArgs.addAll(lst);
            } else {
                flattenedArgs.add(arg);
            }
        }
        return fn.call(flattenedArgs, scope);
    }
}
