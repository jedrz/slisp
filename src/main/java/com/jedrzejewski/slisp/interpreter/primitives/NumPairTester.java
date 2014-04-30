package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.List;

public abstract class NumPairTester extends Primitive {

    public boolean testSubsequentPairs(List<LispObject> args, DoublePairPredicate predicate) {
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

    public interface DoublePairPredicate {
        boolean test(double val1, double val2);
    }
}
