package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.ArgNameShouldBeSymbolException;
import com.jedrzejewski.slisp.interpreter.exceptions.ArgShouldBeVectorException;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.NotAllowedAmpersandUsageException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import com.jedrzejewski.slisp.lispobjects.Function;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Sym;
import com.jedrzejewski.slisp.lispobjects.Vec;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FnForm extends SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Scope scope)
            throws InterpreterException {
        validate(args);

        // Obsługa parametrów.
        List<Sym> argList = new LinkedList<>();
        Vec argVec = (Vec) args.get(0);
        for (LispObject arg : argVec) {
            argList.add((Sym) arg);
        }

        List<LispObject> body = args.subList(1, args.size());
        // Ciało funkcji.
        return new Function(argList, body, scope);
    }

    public void validate(List<LispObject> args) throws InterpreterException {
        ArgsValidator validator = new ArgsValidator(args);

        validator.shouldSize(size -> size >= 2)
                 .ifNotThenThrow(WrongNumberOfArgsException.atLeast(2)
                                                           .is(args.size()));

        // Argument names.
        // Should be a vector.
        validator.shouldAt(0, arg -> arg instanceof Vec)
                 .ifNotThenThrow(ArgShouldBeVectorException.class);
        // Containing symbols.
        validator.shouldAt(0, arg -> {
            Vec argNames = (Vec) arg;
            for (LispObject name : argNames) {
                if (!(name instanceof Sym)) {
                    return false;
                }
            }
            return true;
        }).ifNotThenThrow(ArgNameShouldBeSymbolException.class);
        // And optionally one & followed by a single symbol.
        validator.shouldAt(0, arg -> {
            Vec argNames = (Vec) arg;
            Iterator<LispObject> it = argNames.iterator();
            while (it.hasNext()) {
                Sym sym = (Sym) it.next();
                if (sym.getName().equals("&")) {
                    if (it.hasNext()) {
                        it.next();
                        return !it.hasNext();
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }).ifNotThenThrow(NotAllowedAmpersandUsageException.class);
    }
}
