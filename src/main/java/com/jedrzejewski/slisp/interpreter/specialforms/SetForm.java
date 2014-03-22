package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.Interpreter;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.parser.lispobjects.LispObject;
import com.jedrzejewski.slisp.parser.lispobjects.Symbol;
import java.util.List;

public class SetForm implements SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Interpreter.Evaluator evaluator) {
        Symbol symbol = (Symbol) args.get(0);
        LispObject form = args.get(1);
        LispObject value = evaluator.eval(form);
        Scope scope = evaluator.getScope();
        scope.put(symbol, value);
        return symbol;
    }
}
