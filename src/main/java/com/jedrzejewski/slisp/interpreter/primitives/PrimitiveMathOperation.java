package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.parser.LispObject;
import com.jedrzejewski.slisp.parser.Number;
import java.util.List;
import java.util.stream.DoubleStream;

public abstract class PrimitiveMathOperation implements Primitive {

    public DoubleStream convertToDoubleStream(List<LispObject> args) {
        return args.stream().mapToDouble(arg -> ((Number) arg).getValue());
    }
}
