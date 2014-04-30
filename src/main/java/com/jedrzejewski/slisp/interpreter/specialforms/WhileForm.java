package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import java.util.List;

public class WhileForm extends SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Scope scope)
            throws InterpreterException {
        validate(args);

        LispObject condition = args.get(0);
        List<LispObject> body = args.subList(1, args.size());
        while (new Bool(condition.eval(scope)).isTrue()) {
            for (LispObject expression : body) {
                expression.eval(scope);
            }
        }
        return new Bool(false); // FIXME: change return value?
    }

    public void validate(List<LispObject> args) throws InterpreterException {
        ArgsValidator validator = new ArgsValidator(args);

        validator.shouldSize(size -> size >= 1)
                 .ifNotThenThrow(WrongNumberOfArgsException.atLeast(1)
                                                           .is(args.size()));
    }
}
