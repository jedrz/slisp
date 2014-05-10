package com.jedrzejewski.slisp.lispobjects;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import java.util.List;

// FIXME: macro shouldn't extend the Function class.
// Create common class for macro and function classes.

public class Macro extends Function {

    public Macro(List<Sym> argNames, LispObject body, Scope scope) {
        super(argNames, body, scope);
    }

    public Macro(List<Sym> argNames, List<LispObject> bodyList, Scope scope) {
        super(argNames, bodyList, scope);
    }

    /**
     * Expand macro body by evaluating the body with non evaluated arguments.
     * Then the expand result is evaluated again.
     * @param args unevaluated args
     * @param scope current scope
     * @return the result of macro expansion and the expansion evaluation result
     * @throws InterpreterException
     */
    @Override
    public LispObject call(List<LispObject> args, Scope scope)
            throws InterpreterException {
        Scope wrapperScope = buildScopeWithArgsDontEval(args, scope);
        LispObject expanded = getBody().eval(wrapperScope);
        return expanded.eval(scope);
    }
}
