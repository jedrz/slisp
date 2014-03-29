package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Lst;
import com.jedrzejewski.slisp.lispobjects.Nil;
import java.util.List;

public class RestPrimitive implements Primitive {

    @Override
    public LispObject call(List<LispObject> args) {
        if (args.size() == 1) {
            LispObject obj = args.get(0);
            if (obj instanceof Lst) {
                Lst lst = (Lst) obj;
                if (lst.isEmpty()) {
                    return new Lst();
                } else {
                    Lst rest = new Lst();
                    rest.addAll(lst.subList(1, lst.size()));
                    return rest;
                }
            }
        }
        return new Nil(); // TODO: return other value?
    }
}