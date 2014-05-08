package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.ArgShouldBeSymbolException;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Sym;
import java.util.List;

public class DefnForm extends SpecialForm {

    // FIXME: ugly.
    protected FnForm fnForm = new FnForm();

    @Override
    public LispObject call(List<LispObject> args, Scope scope)
            throws InterpreterException {
        Sym fnName = (Sym) args.get(0);
        scope.put(fnName, fnForm.call(args.subList(1, args.size()), scope));
        return fnName;
    }

    // We need to call above fnForm validate method, and can't extend FnForm
    // because calling call super call method results in calling DefnForm
    // validate method!.
    @Override
    public void validate(List<LispObject> args) throws InterpreterException {
        ArgsValidator validator = new ArgsValidator(args);

        validator.shouldSize(size -> size >= 3)
                 .ifNotThenThrow(WrongNumberOfArgsException.atLeast(3)
                                                           .is(args.size()));

        validator.shouldAt(0, arg -> arg instanceof Sym)
                 .ifNotThenThrow(ArgShouldBeSymbolException.class);

        fnForm.validate(args.subList(1, args.size()));
    }
}
