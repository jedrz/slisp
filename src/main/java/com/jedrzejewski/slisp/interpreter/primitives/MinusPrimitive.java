package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.List;

public class MinusPrimitive extends MathOperationPrimitive {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope) {
        double result;
        if (args.size() == 1) {
            result = -convertToDoubleStream(args).toArray()[0];
        } else {
            result = convertToDoubleStream(args)
                    .reduce((a, b) -> (a - b))
                    .getAsDouble();
        }
        return new Num(result);
    }
}
