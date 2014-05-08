package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.exceptions.ArgShouldBeNumException;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.List;

public abstract class NumPairTester extends Primitive {

    public boolean testSubsequentPairs(List<LispObject> args,
                                       DoublePairPredicate predicate)
            throws InterpreterException {
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

    public void validate(ArgsValidator validator) throws InterpreterException {
        validator.shouldSize(size -> size >= 2)
                 .ifNotThenThrow(
                         WrongNumberOfArgsException.atLeast(2)
                                                   .is(validator.getArgsSize()));

        validator.eachShould(arg -> arg instanceof Num)
                 .ifNotThenThrow(ArgShouldBeNumException.class);
    }

    public interface DoublePairPredicate {
        boolean test(double val1, double val2);
    }
}
