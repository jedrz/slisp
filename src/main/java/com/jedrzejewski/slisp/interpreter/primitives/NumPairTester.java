package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.List;

public abstract class NumPairTester extends Primitive {

    public boolean testSubsequentPairs(List<LispObject> args,
                                       DoublePairPredicate predicate)
            throws InterpreterException {
        validate(args);

        double prevValue = ((Num) args.get(0)).getValue();
        for (LispObject arg : args.subList(1, args.size())) {
            double curValue = ((Num) arg).getValue();
            if (!predicate.test(prevValue, curValue)) {
                return false;
            } else {
                prevValue = curValue;
            }
        }
        return true;
    }

    public void validate(List<LispObject> args) throws InterpreterException {
        if (args.size() <= 1) {
            throw WrongNumberOfArgsException.atLeast(2).is(args.size());
        }
        // TODO: check types.
    }

    public interface DoublePairPredicate {
        boolean test(double val1, double val2);
    }
}
