package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.lispobjects.Callable;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Lst;
import com.jedrzejewski.slisp.lispobjects.Sym;
import java.util.List;
import java.util.stream.Collectors;

public class ApplyPrimitive extends Primitive {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope)
            throws InterpreterException {
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
        // We need to quote any list in flattened args to avoid evaluation.
        // See second test of apply primitive.
        List<LispObject> quotedAndFlattenedArgs = flattenedArgs
                .stream()
                .map(arg -> {
                    if (arg instanceof Lst) {
                        Lst quoted = new Lst();
                        quoted.add(new Sym("quote"));
                        quoted.add(arg);
                        return quoted;
                    } else {
                        return arg;
                    }
                })
                .collect(Collectors.toList());
        return fn.call(quotedAndFlattenedArgs, scope);
    }
}
