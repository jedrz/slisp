package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Str;
import java.util.List;

public class StringPredicatePrimitive extends Primitive {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope)
            throws InterpreterException {
        validate(args);
        return new Bool(args.get(0) instanceof Str);
    }

    public void validate(List<LispObject> args) throws InterpreterException {
        ArgsValidator validator = new ArgsValidator(args);

        validator.shouldSize(size -> size == 1)
                 .ifNotThenThrow(WrongNumberOfArgsException.exactly(1)
                                                           .is(args.size()));
    }
}
