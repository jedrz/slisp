package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.Scope;
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
        validate(args);
        // TODO: check types.
        Str str = (Str) args.get(0);
        Num start = (Num) args.get(1);
        Num end = (Num) args.get(2);
        return new Str(str.getString()
                          .substring(start.getValue().intValue(),
                                     end.getValue().intValue()
                          ));
    }

    public void validate(List<LispObject> args) throws InterpreterException {
        if (args.size() != 3) {
            throw WrongNumberOfArgsException.exactly(3).is(args.size());
        }
    }
}
