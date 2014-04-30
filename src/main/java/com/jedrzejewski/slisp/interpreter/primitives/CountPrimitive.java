package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.ArgShouldBeListOrStringException;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Lst;
import com.jedrzejewski.slisp.lispobjects.Num;
import com.jedrzejewski.slisp.lispobjects.Str;
import java.util.List;

public class CountPrimitive extends Primitive {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope)
            throws InterpreterException {
        validate(args);

        LispObject obj = args.get(0);
        if (obj instanceof Lst) {
            Lst lst = (Lst) obj;
            return new Num(lst.size());
        } else if (obj instanceof Str) {
            Str str = (Str) obj;
            return new Num(str.getString().length());
        }
        return new Num(-1);
    }

    public void validate(List<LispObject> args) throws InterpreterException {
        ArgsValidator validator = new ArgsValidator(args);

        validator.shouldSize(size -> size == 1)
                 .ifNotThenThrow(WrongNumberOfArgsException.exactly(1)
                                                           .is(args.size()));

        validator.eachShould(arg -> arg instanceof Lst || arg instanceof Str)
                .ifNotThenThrow(ArgShouldBeListOrStringException.class);
    }
}
