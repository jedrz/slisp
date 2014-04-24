package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.lispobjects.Callable;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import java.util.LinkedList;
import java.util.List;

public abstract class Primitive extends Callable {

    public abstract LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope)
            throws InterpreterException;

    @Override
    public LispObject call(List<LispObject> args, Scope scope) throws InterpreterException {
        List<LispObject> evaluatedArgs = new LinkedList<>();
        for (LispObject o : args) {
            evaluatedArgs.add(o.eval(scope));
        }
        return callWithEvaluatedArgs(evaluatedArgs, scope);
    }
}
