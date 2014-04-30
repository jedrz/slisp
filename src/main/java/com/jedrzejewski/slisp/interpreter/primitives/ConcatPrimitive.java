package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Lst;
import java.util.List;

public class ConcatPrimitive extends Primitive {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope)
            throws InterpreterException {
        validate(args);
        Lst concatResult = new Lst();
        // TODO: args have to be lists.
        args.forEach(lst -> concatResult.addAll((Lst) lst));
        return concatResult;
    }

    public void validate(List<LispObject> args) throws InterpreterException {
    }
}
