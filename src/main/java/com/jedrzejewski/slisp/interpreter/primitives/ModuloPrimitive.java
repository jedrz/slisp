package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.ArgShouldBeNum;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.List;

public class ModuloPrimitive extends Primitive {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope)
            throws InterpreterException {
        // TODO: require 2 numbers.
        Num num, div;
        try {
            num = (Num) args.get(0);
            div = (Num) args.get(1);
        } catch (ClassCastException e) {
            throw new ArgShouldBeNum();
        }
        return new Num(num.getValue() % div.getValue());
    }
}
