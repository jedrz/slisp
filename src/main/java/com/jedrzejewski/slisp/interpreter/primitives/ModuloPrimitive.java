package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.ArgShouldBeNumException;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.List;

public class ModuloPrimitive extends Primitive {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope)
            throws InterpreterException {
        validate(args);

        Num num = (Num) args.get(0);
        Num div = (Num) args.get(1);
        return new Num(num.getValue() % div.getValue());
    }

    public void validate(List<LispObject> args) throws InterpreterException {
        ArgsValidator validator = new ArgsValidator(args);

        validator.shouldSize(size -> size == 2)
                 .ifNotThenThrow(WrongNumberOfArgsException.exactly(2)
                                                           .is(args.size()));

        validator.eachShould(arg -> arg instanceof Num)
                 .ifNotThenThrow(ArgShouldBeNumException.class);
    }
}
