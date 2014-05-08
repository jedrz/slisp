package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import java.util.List;

public class QuoteForm extends SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Scope scope)
            throws InterpreterException {
        return args.get(0);
    }

    @Override
    public void validate(ArgsValidator validator) throws InterpreterException {
        validator.shouldSize(size -> size == 1)
                 .ifNotThenThrow(
                         WrongNumberOfArgsException.exactly(1)
                                                   .is(validator.getArgsSize()));
    }
}
