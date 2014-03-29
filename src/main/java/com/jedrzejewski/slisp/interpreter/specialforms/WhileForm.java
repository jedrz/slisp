package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.Interpreter;
import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import java.util.List;

public class WhileForm implements SpecialForm{

    @Override
    public LispObject call(List<LispObject> args, Interpreter.Evaluator evaluator) {
        // TODO: check number of given args.
        LispObject condition = args.get(0);
        List<LispObject> body = args.subList(1, args.size());
        while (new Bool(evaluator.eval(condition)).isTrue()) {
            for (LispObject expression : body) {
                evaluator.eval(expression);
            }
        }
        return new Bool(false); // TODO: change return value
    }
}
