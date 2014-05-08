package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.List;

public class NumberPredicatePrimitive extends Primitive {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope)
            throws InterpreterException {
        return new Bool(args.get(0) instanceof Num);
    }

    public void validate(ArgsValidator validator) throws InterpreterException {
        validator.shouldSize(size -> size == 1)
                 .ifNotThenThrow(
                         WrongNumberOfArgsException.exactly(1)
                                                   .is(validator.getArgsSize()));
    }
}
