package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.parser.lispobjects.LispObject;
import com.jedrzejewski.slisp.parser.lispobjects.Num;
import java.util.List;
import java.util.stream.DoubleStream;

public abstract class MathOperationPrimitive implements Primitive {

    public DoubleStream convertToDoubleStream(List<LispObject> args) {
        return args.stream().mapToDouble(arg -> ((Num) arg).getValue());
    }
}
