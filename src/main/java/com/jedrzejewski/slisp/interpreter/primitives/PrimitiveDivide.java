package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.parser.lispobjects.LispObject;
import com.jedrzejewski.slisp.parser.lispobjects.Num;
import java.util.List;

public class PrimitiveDivide extends PrimitiveMathOperation {

    @Override
    public LispObject call(List<LispObject> args) {
        double result = convertToDoubleStream(args)
                .reduce((a, b) -> (a / b))
                .getAsDouble();
        return new Num(result);
    }
}