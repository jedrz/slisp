package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import java.util.List;

public class EvalForm extends SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Scope scope) {
        // TODO: only one arg.
        return args.get(0).eval(scope).eval(scope); // OMG
    }
}
