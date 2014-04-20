package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Lst;
import com.jedrzejewski.slisp.lispobjects.Nil;
import java.util.List;

public class FirstPrimitive extends Primitive {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope) {
        if (args.size() == 1) {
            LispObject obj = args.get(0);
            if (obj instanceof Lst) {
                Lst lst = (Lst) obj;
                if (lst.isEmpty()) {
                    return new Nil();
                } else {
                    return lst.get(0);
                }
            }
        }
        return new Nil();
    }
}
