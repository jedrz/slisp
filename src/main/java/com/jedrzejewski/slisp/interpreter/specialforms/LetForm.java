package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Lst;
import com.jedrzejewski.slisp.lispobjects.Sym;
import com.jedrzejewski.slisp.lispobjects.Vec;
import java.util.Iterator;
import java.util.List;

public class LetForm extends SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Scope scope) throws InterpreterException {
        if (args.get(0) instanceof Vec) {
            Vec bindings = (Vec) args.get(0);
            Scope letScope = bindSymbols(bindings, scope);
            Lst doForm = new Lst();
            doForm.add(new Sym("do"));
            doForm.addAll(args.subList(1, args.size()));
            return doForm.eval(letScope);
        } else {
            // TODO: syntax error
        }
        return null;
    }

    private Scope bindSymbols(Vec bindings, Scope scope) throws InterpreterException {
        Scope letScope = new Scope(scope);
        if (bindings.size() % 2 == 0) {
            Iterator<LispObject> it = bindings.iterator();
            while (it.hasNext()) {
                LispObject varName = it.next();
                LispObject form = it.next();
                if (varName instanceof Sym) {
                    letScope.put(
                            (Sym) varName,
                            form.eval(letScope)
                    );
                } else {
                    // TODO: var name should be symbol
                }
            }
        } else {
            // TODO: no value for symbol
        }
        return letScope;
    }
}
