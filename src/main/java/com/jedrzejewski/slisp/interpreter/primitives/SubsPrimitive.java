package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.ArgShouldBeNumException;
import com.jedrzejewski.slisp.interpreter.exceptions.ArgShouldBeStringException;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Num;
import com.jedrzejewski.slisp.lispobjects.Str;
import java.util.List;

public class SubsPrimitive extends Primitive {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope)
            throws InterpreterException {
        Str str = (Str) args.get(0);
        Num start = (Num) args.get(1);
        Num end = (Num) args.get(2);

        // TODO: check constraint values.
        return new Str(str.getString()
                          .substring(start.getValue().intValue(),
                                     end.getValue().intValue()
                          ));
    }

    @Override
    public void validate(List<LispObject> args) throws InterpreterException {
        ArgsValidator validator = new ArgsValidator(args);

        validator.shouldSize(size -> size == 3)
                 .ifNotThenThrow(WrongNumberOfArgsException.exactly(3)
                                                           .is(args.size()));

        validator.shouldAt(0, arg -> arg instanceof Str)
                 .ifNotThenThrow(ArgShouldBeStringException.class);
        validator.shouldAt(1, arg -> arg instanceof Num)
                 .ifNotThenThrow(ArgShouldBeNumException.class);
        validator.shouldAt(2, arg -> arg instanceof Num)
                 .ifNotThenThrow(ArgShouldBeNumException.class);

    }
}
