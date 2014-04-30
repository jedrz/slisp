package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Lst;
import com.jedrzejewski.slisp.lispobjects.Nil;
import java.util.List;

public class FirstPrimitive extends Primitive {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope)
            throws InterpreterException{
        validate(args);

        LispObject obj = args.get(0);
        if (obj instanceof Lst) {
            Lst lst = (Lst) obj;
            if (lst.isEmpty()) {
                return new Nil();
            } else {
                return lst.get(0);
            }
        } else {
            // TODO: wrong type.
        }
        return new Nil();
    }

    public void validate(List<LispObject> args) throws InterpreterException {
        ArgsValidator validator = new ArgsValidator(args);

        validator.shouldSize(size -> size == 1)
                 .ifNotThenThrow(WrongNumberOfArgsException.exactly(1)
                                                           .is(args.size()));
    }
}
