package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.List;

public class ModuloPrimitive extends Primitive {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope) {
        // TODO: require 2 numbers.
        Num num = (Num) args.get(0);
        Num div = (Num) args.get(1);
        return new Num(num.getValue() % div.getValue());
    }
}
