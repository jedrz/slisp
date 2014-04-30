package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.Scope;
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

        if (args.size() == 1) {
            LispObject obj = args.get(0);
            if (obj instanceof Lst) {
                Lst lst = (Lst) obj;
                return new Num(lst.size());
            } else if (obj instanceof Str) {
                Str str = (Str) obj;
                return new Num(str.getString().length());
            } else {
                // TODO: invalid type.
            }
        }
        return new Num(-1);
    }

    public void validate(List<LispObject> args) throws InterpreterException {
        if (args.size() != 1) {
            throw WrongNumberOfArgsException.exactly(1).is(args.size());
        }
    }
}
