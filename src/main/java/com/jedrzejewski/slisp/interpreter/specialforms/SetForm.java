package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.Evaluater;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.parser.lispobjects.LispObject;
import com.jedrzejewski.slisp.parser.lispobjects.Symbol;
import java.util.List;

public class SetForm implements SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Evaluater evaluater, Scope scope) {
        Symbol symbol = (Symbol) args.get(0);
        LispObject form = args.get(1);
        LispObject value = evaluater.eval(form, scope);
        scope.put(symbol, value);
        return symbol;
    }
}
