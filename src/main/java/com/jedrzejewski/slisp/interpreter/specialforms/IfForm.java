package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Lst;
import com.jedrzejewski.slisp.lispobjects.Sym;
import java.util.List;

public class IfForm extends SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Scope scope)
            throws InterpreterException {
        Bool condition = new Bool(args.get(0).eval(scope));
        if (condition.isTrue()) {
            return args.get(1).eval(scope);
        } else {
            Lst doForm = new Lst();
            doForm.add(new Sym("do"));
            doForm.addAll(args.subList(2, args.size()));
            return doForm.eval(scope);
        }
    }

    @Override
    public void validate(List<LispObject> args) throws InterpreterException {
        ArgsValidator validator = new ArgsValidator(args);

        validator.shouldSize(size -> size == 3)
                 .ifNotThenThrow(WrongNumberOfArgsException.exactly(3)
                                                           .is(args.size()));
    }
}
