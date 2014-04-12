package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Nil;
import java.util.List;

public class QuoteForm extends SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Scope scope) {
        if (args.size() >= 1) {
            return args.get(0);
        } else {
            return new Nil();
        }
    }
}
