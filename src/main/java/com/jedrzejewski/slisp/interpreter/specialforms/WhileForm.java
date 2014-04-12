package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import java.util.List;

public class WhileForm extends SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Scope scope) {
        // TODO: check number of given args.
        LispObject condition = args.get(0);
        List<LispObject> body = args.subList(1, args.size());
        while (new Bool(condition.eval(scope)).isTrue()) {
            for (LispObject expression : body) {
                expression.eval(scope);
            }
        }
        return new Bool(false); // TODO: change return value
    }
}
