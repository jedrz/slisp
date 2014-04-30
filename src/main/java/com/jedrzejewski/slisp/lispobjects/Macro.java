package com.jedrzejewski.slisp.lispobjects;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import java.util.List;

public class Macro extends Function {

    public Macro(List<Sym> argNames, LispObject body, Scope scope) {
        super(argNames, body, scope);
    }

    public Macro(List<Sym> argNames, List<LispObject> bodyList, Scope scope) {
        super(argNames, bodyList, scope);
    }

    @Override
    public LispObject call(List<LispObject> args, Scope scope)
            throws InterpreterException {
        Scope wrapperScope = buildScopeWithArgsDontEval(args, scope);
        LispObject expanded = getBody().eval(wrapperScope);
        return expanded.eval(scope);
    }
}
