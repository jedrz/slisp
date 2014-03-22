package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.parser.LispObject;
import com.jedrzejewski.slisp.parser.Number;
import java.util.List;

public class PrimitiveMinus extends PrimitiveMathOperation {

    @Override
    public LispObject call(List<LispObject> args) {
        double result;
        if (args.size() == 1) {
            result = -convertToDoubleStream(args).toArray()[0];
        } else {
            result = convertToDoubleStream(args)
                    .reduce((a, b) -> (a - b))
                    .getAsDouble();
        }
        return new Number(result);
    }
}
