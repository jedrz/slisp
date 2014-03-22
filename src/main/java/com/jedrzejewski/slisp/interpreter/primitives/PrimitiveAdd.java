package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.parser.lispobjects.LispObject;
import com.jedrzejewski.slisp.parser.lispobjects.Num;
import java.util.List;

public class PrimitiveAdd extends PrimitiveMathOperation {

    @Override
    public LispObject call(List<LispObject> args) {
        double result = convertToDoubleStream(args).sum();
        return new Num(result);
    }
}
