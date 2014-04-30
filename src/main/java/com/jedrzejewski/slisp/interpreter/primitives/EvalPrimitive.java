package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import java.util.List;

public class EvalPrimitive extends Primitive {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope)
            throws InterpreterException {
        return args.get(0).eval(scope);
    }

    public void validate(List<LispObject> args) throws InterpreterException {
        ArgsValidator validator = new ArgsValidator(args);

        validator.shouldSize(size -> size == 1)
                 .ifNotThenThrow(WrongNumberOfArgsException.exactly(1)
                                                           .is(args.size()));
    }
}
