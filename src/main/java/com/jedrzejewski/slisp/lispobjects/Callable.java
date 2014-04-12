package com.jedrzejewski.slisp.lispobjects;

import com.jedrzejewski.slisp.interpreter.Scope;
import java.util.List;

public abstract class Callable implements LispObject {

    public abstract LispObject call(List<LispObject> args, Scope scope);

    @Override
    public LispObject eval(Scope scope) {
        return this;
    }
}
