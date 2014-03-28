package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.Interpreter;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import java.util.List;

public class DoForm implements SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Interpreter.Evaluator evaluator) {
        for (LispObject arg : args.subList(0, args.size() - 1)) {
            evaluator.eval(arg);
        }
        return evaluator.eval(args.get(args.size() - 1));
    }
}
