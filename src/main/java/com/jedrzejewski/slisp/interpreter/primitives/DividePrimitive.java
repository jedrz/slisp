package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.List;

public class DividePrimitive extends MathOperationPrimitive {

    @Override
    public LispObject call(List<LispObject> args) {
        double result = convertToDoubleStream(args)
                .reduce((a, b) -> (a / b))
                .getAsDouble();
        return new Num(result);
    }
}