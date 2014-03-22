package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.Primitive;
import com.jedrzejewski.slisp.parser.LispObject;
import com.jedrzejewski.slisp.parser.Number;
import java.util.List;

public class PrimitiveAdd implements Primitive {
    @Override
    public LispObject call(List<LispObject> args) {
        double sum = 0.0;
        for (LispObject arg : args) {
            // TODO: rzuć wyjątek gdy argumenty nie są liczbami
            Number number = (Number) arg;
            sum += number.getValue();
        }
        return new Number(sum);
    }

    @Override
    public int minArgs() {
        return 2;
    }

    @Override
    public boolean varArgs() {
        return true;
    }
}
