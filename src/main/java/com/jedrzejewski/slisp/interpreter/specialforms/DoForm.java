package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import java.util.List;

public class DoForm extends SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Scope scope) {
        for (LispObject arg : args.subList(0, args.size() - 1)) {
            arg.eval(scope);
        }
        return args.get(args.size() - 1).eval(scope);
    }
}
