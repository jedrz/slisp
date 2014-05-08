package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import java.util.List;

public class DoForm extends SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Scope scope)
            throws InterpreterException {
        for (LispObject arg : args.subList(0, args.size() - 1)) {
            arg.eval(scope);
        }
        return args.get(args.size() - 1).eval(scope);
    }

    @Override
    public void validate(ArgsValidator validator) throws InterpreterException {
        validator.shouldSize(size -> size >= 1)
                 .ifNotThenThrow(
                         WrongNumberOfArgsException.atLeast(1)
                                                   .is(validator.getArgsSize()));
    }
}
