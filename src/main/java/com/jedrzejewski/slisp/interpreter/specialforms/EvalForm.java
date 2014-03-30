package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.Interpreter;
import com.jedrzejewski.slisp.interpreter.primitives.Primitive;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import java.util.List;

public class EvalForm implements SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Interpreter.Evaluator evaluator) {
        // TODO: only one arg.
        return evaluator.eval(evaluator.eval(args.get(0))); // OMG
    }
}
