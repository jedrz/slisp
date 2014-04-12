package com.jedrzejewski.slisp.lispobjects;

import com.jedrzejewski.slisp.interpreter.Scope;

public interface LispObject {
    LispObject eval(Scope scope);
}
