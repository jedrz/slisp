package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.Interpreter;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Lst;
import com.jedrzejewski.slisp.lispobjects.Sym;
import java.util.List;

public class DefnForm implements SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Interpreter.Evaluator evaluator) {
        // TODO: don't use set!
        Lst setFnForm = new Lst();
        setFnForm.add(new Sym("set!"));
        setFnForm.add(args.get(0)); // Name of function.
        Lst fnForm = new Lst();
        fnForm.add(new Sym("fn"));
        fnForm.addAll(args.subList(1, args.size()));
        setFnForm.add(fnForm);
        return evaluator.eval(setFnForm);
    }
}
