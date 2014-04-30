package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import java.util.List;

public class LessEqualPrimitive extends NumPairTester {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope)
            throws InterpreterException {
        boolean result = testSubsequentPairs(
                args,
                (a, b) -> a <= b
        );
        return new Bool(result);
    }
}
