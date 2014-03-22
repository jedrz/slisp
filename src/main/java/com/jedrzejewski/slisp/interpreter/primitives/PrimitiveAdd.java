package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.parser.LispObject;
import com.jedrzejewski.slisp.parser.Number;
import java.util.List;

public class PrimitiveAdd extends PrimitiveMathOperation {

    @Override
    public LispObject call(List<LispObject> args) {
        double result = convertToDoubleStream(args).sum();
        return new Number(result);
    }
}
