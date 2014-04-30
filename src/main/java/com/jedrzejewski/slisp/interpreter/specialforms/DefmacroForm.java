package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Macro;
import com.jedrzejewski.slisp.lispobjects.Sym;
import com.jedrzejewski.slisp.lispobjects.Vec;
import java.util.LinkedList;
import java.util.List;

public class DefmacroForm extends DefnForm {

    // FIXME: macro shouldn't extend a function form.

    @Override
    public LispObject call(List<LispObject> args, Scope scope)
            throws InterpreterException {
        validate(args);

        // Obsługa parametrów.
        List<Sym> argList = new LinkedList<>();
        Vec argVec = (Vec) args.get(1);
        for (LispObject arg : argVec) {
            argList.add((Sym) arg);
        }

        Sym macroName = (Sym) args.get(0);
        List<LispObject> body = args.subList(2, args.size());
        Macro macro = new Macro(argList, body, scope);
        scope.put(macroName, macro);
        return macroName;
    }
}
