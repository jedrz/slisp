package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Macro;
import com.jedrzejewski.slisp.lispobjects.Sym;
import com.jedrzejewski.slisp.lispobjects.Vec;
import java.util.LinkedList;
import java.util.List;

public class DefmacroForm extends SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Scope scope) {
        // Obsługa parametrów.
        List<Sym> argList = new LinkedList<>();
        if (args.get(1) instanceof Vec) {
            Vec argVec = (Vec) args.get(1);
            for (LispObject arg : argVec) {
                if (arg instanceof Sym) {
                    argList.add((Sym) arg);
                } else {
                    // TODO: syntax error
                }
            }
        } else {
            // TODO: Syntax Error
        }

        Sym macroName = (Sym) args.get(0);
        List<LispObject> body = args.subList(2, args.size());
        Macro macro = new Macro(argList, body, scope);
        scope.put(macroName, macro);
        return macroName;
    }
}
