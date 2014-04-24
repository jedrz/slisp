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

    protected LispObject expand(List<LispObject> args, Scope scope)
            throws InterpreterException{
        Scope wrapperScope = new Scope(getScope());
        for (int i = 0; i < getArgNames().size(); ++i) {
            Sym argName = getArgNames().get(i);
            if (argName.getName().equals("&")) {
                // TODO: there is no symbol after &?
                argName = getArgNames().get(i + 1);
                Lst restArgs = new Lst();
                for (LispObject o : args.subList(i, args.size())) {
                    restArgs.add(o);
                }
                wrapperScope.put(argName, restArgs);
                break;
            }
            LispObject arg = args.get(i);
            wrapperScope.put(argName, arg);
        }
        return getBody().eval(wrapperScope);
    }

    @Override
    public LispObject call(List<LispObject> args, Scope scope)
            throws InterpreterException {
        LispObject expanded = expand(args, scope);
        return expanded.eval(scope);
    }
}
