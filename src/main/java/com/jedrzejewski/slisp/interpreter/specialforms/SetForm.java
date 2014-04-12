package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Sym;
import java.util.List;

public class SetForm extends SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Scope scope) {
        Sym sym = (Sym) args.get(0);
        LispObject form = args.get(1);
        LispObject value = form.eval(scope);
        scope.put(sym, value);
        return sym;
    }
}
