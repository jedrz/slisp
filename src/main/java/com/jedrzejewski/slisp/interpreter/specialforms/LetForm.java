package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.ArgShouldBeVectorException;
import com.jedrzejewski.slisp.interpreter.exceptions.BindingShouldBeSymbolException;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.NoValueForSymbolException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Lst;
import com.jedrzejewski.slisp.lispobjects.Sym;
import com.jedrzejewski.slisp.lispobjects.Vec;
import java.util.Iterator;
import java.util.List;

public class LetForm extends SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Scope scope)
            throws InterpreterException {
        Vec bindings = (Vec) args.get(0);
        Scope letScope = bindSymbols(bindings, scope);
        Lst doForm = new Lst();
        doForm.add(new Sym("do"));
        doForm.addAll(args.subList(1, args.size()));
        return doForm.eval(letScope);
    }

    private Scope bindSymbols(Vec bindings, Scope scope)
            throws InterpreterException {
        Scope letScope = new Scope(scope);
        Iterator<LispObject> it = bindings.iterator();
        while (it.hasNext()) {
            LispObject varName = it.next();
            LispObject form = it.next();
            letScope.put(
                    (Sym) varName,
                    form.eval(letScope)
            );
        }
        return letScope;
    }

    @Override
    public void validate(ArgsValidator validator) throws InterpreterException {
        validator.shouldSize(size -> size >= 2)
                 .ifNotThenThrow(
                         WrongNumberOfArgsException.atLeast(2)
                                                   .is(validator.getArgsSize()));
        // Bindings.
        // Are a vector.
        validator.shouldAt(0, arg -> arg instanceof Vec)
                 .ifNotThenThrow(ArgShouldBeVectorException.class);
        // With even size.
        validator.shouldAt(0, arg -> {
            Vec bindings = (Vec) arg;
            return bindings.size() % 2 == 0;
        }).ifNotThenThrow(NoValueForSymbolException.class);
        // And every first element of a pair is a symbol.
        validator.shouldAt(0, arg -> {
            Vec bindings = (Vec) arg;
            Iterator<LispObject> it = bindings.iterator();
            while (it.hasNext()) {
                LispObject varName = it.next();
                LispObject form = it.next();
                if (!(varName instanceof Sym)) {
                    return false;
                }
            }
            return true;
        }).ifNotThenThrow(BindingShouldBeSymbolException.class);
    }
}
