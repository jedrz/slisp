package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.Interpreter;
import com.jedrzejewski.slisp.lispobjects.Function;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Sym;
import com.jedrzejewski.slisp.lispobjects.Vec;
import java.util.LinkedList;
import java.util.List;

public class FnForm implements SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Interpreter.Evaluator evaluator) {
        // Obsługa parametrów.
        List<Sym> argList = new LinkedList<>();
        if (args.get(0) instanceof Vec) {
            Vec argVec = (Vec) args.get(0);
            for (LispObject arg : argVec) {
                if (arg instanceof Sym) {
                    argList.add((Sym) arg);
                } else {
                    // TODO: syntax error
                }
            }
        } else {
            // TODO: Syntax Error
        }

        List<LispObject> body = args.subList(1, args.size());
        // Ciało funkcji.
        return new Function(argList, body, evaluator.getScope());
    }
}
