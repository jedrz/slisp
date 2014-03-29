package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import java.util.List;

public class LessEqualsPrimitive implements Primitive {

    @Override
    public LispObject call(List<LispObject> args) {
        boolean result = NumsTester.test(
                args,
                (a, b) -> a <= b
        );
        return new Bool(result);
    }
}
