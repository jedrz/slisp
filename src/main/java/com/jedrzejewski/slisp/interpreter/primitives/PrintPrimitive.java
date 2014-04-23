package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Nil;
import com.jedrzejewski.slisp.lispobjects.Str;
import java.util.List;

public class PrintPrimitive extends Primitive {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope) {
        args.forEach(arg -> {
            if (arg instanceof Str) {
                // I don't want to see quotes...
                Str str = (Str) arg;
                System.out.print(str.getString());
            } else {
                System.out.print(arg);
            }
            System.out.print(" ");
        });
        return new Nil();
    }
}
