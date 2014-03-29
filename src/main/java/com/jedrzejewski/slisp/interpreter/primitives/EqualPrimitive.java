package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.List;

public class EqualPrimitive extends MathOperationPrimitive {

    @Override
    public LispObject call(List<LispObject> args) {
        double firstNumber = ((Num) args.get(0)).getValue();
        boolean result = convertToDoubleStream(args)
                .allMatch(value -> value == firstNumber);
        return new Bool(result);
    }
}
