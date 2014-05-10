package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.ArgShouldBeListException;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Lst;
import java.util.List;

public class ConcatPrimitive extends Primitive {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope)
            throws InterpreterException {
        Lst concatResult = new Lst();
        for (LispObject lst : args) {
            concatResult.addAll((Lst) lst);
        }
        return concatResult;
    }

    @Override
    public void validate(ArgsValidator validator) throws InterpreterException {
        validator.eachShould(arg -> arg instanceof Lst)
                 .ifNotThenThrow(ArgShouldBeListException.class);
    }
}
