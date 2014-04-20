package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.lispobjects.Callable;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Primitive extends Callable {

    public abstract LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope);

    @Override
    public LispObject call(List<LispObject> args, Scope scope) {
        List<LispObject> evaluatedArgs = args
                .stream()
                .map(o -> o.eval(scope))
                .collect(Collectors.toList());
        return callWithEvaluatedArgs(evaluatedArgs, scope);
    }
}
