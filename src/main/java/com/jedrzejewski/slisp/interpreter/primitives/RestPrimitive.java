package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Lst;
import com.jedrzejewski.slisp.lispobjects.Nil;
import java.util.List;

public class RestPrimitive extends Primitive {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope)
            throws InterpreterException {
        validate(args);

        LispObject obj = args.get(0);
        if (obj instanceof Lst) {
            Lst lst = (Lst) obj;
            if (lst.isEmpty()) {
                return new Lst();
            } else {
                Lst rest = new Lst();
                rest.addAll(lst.subList(1, lst.size()));
                return rest;
            }
        } else {
            // TODO: wrong type
        }
        return new Nil(); // TODO: return other value?
    }

    public void validate(List<LispObject> args) throws InterpreterException {
        if (args.size() != 1) {
            throw WrongNumberOfArgsException.exactly(1).is(args.size());
        }
    }
}
