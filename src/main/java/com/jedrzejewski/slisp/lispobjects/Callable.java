package com.jedrzejewski.slisp.lispobjects;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import java.util.List;

public abstract class Callable implements LispObject {

    public abstract LispObject call(List<LispObject> args, Scope scope)
            throws InterpreterException;

    public abstract void validate(List<LispObject> args)
            throws InterpreterException;

    @Override
    public LispObject eval(Scope scope) {
        return this;
    }
}
