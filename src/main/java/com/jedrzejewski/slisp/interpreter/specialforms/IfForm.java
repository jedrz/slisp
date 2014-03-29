package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.Interpreter;
import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Lst;
import com.jedrzejewski.slisp.lispobjects.Sym;
import java.util.List;

public class IfForm implements SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Interpreter.Evaluator evaluator) {
        // TODO: make sure 3 or more args are passed.
        Bool condition = new Bool(evaluator.eval(args.get(0)));
        if (condition.isTrue()) {
            return evaluator.eval(args.get(1));
        } else {
            Lst doForm = new Lst();
            doForm.add(new Sym("do"));
            doForm.addAll(args.subList(2, args.size()));
            return evaluator.eval(doForm);
        }
    }
}
