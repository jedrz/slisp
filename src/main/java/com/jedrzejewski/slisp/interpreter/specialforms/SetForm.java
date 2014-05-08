package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.ArgShouldBeSymbolException;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Sym;
import java.util.List;

public class SetForm extends SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Scope scope)
            throws InterpreterException {
        Sym sym = (Sym) args.get(0);
        LispObject form = args.get(1);
        LispObject value = form.eval(scope);
        scope.put(sym, value);
        return sym;
    }

    @Override
    public void validate(List<LispObject> args) throws InterpreterException {
        ArgsValidator validator = new ArgsValidator(args);

        validator.shouldSize(size -> size == 2)
                 .ifNotThenThrow(WrongNumberOfArgsException.exactly(2)
                                                           .is(args.size()));
        validator.shouldAt(0, arg -> arg instanceof Sym)
                 .ifNotThenThrow(ArgShouldBeSymbolException.class);
    }
}
