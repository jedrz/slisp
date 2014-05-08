package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.ArgShouldBeCallableException;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
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
        // We need to quote all args before calling since they will be evaluated again.
        // See second test of apply primitive.
        List<LispObject> quotedAndFlattenedArgs = flattenedArgs
                .stream()
                .map(arg -> {
                    Lst lst = new Lst();
                    lst.add(new Sym("quote"));
                    lst.add(arg);
                    return lst;
                })
                .collect(Collectors.toList());
        return fn.call(quotedAndFlattenedArgs, scope);
    }

    @Override
    public void validate(List<LispObject> args) throws InterpreterException {
        ArgsValidator validator = new ArgsValidator(args);

        validator.shouldSize(size -> size >= 2)
                 .ifNotThenThrow(WrongNumberOfArgsException.atLeast(2)
                                                           .is(args.size()));

        validator.shouldAt(0, arg -> arg instanceof Callable)
                 .ifNotThenThrow(ArgShouldBeCallableException.class);
    }
}
