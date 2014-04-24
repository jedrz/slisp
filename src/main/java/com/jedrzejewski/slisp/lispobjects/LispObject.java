package com.jedrzejewski.slisp.lispobjects;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;

public interface LispObject {
    LispObject eval(Scope scope) throws InterpreterException;
}
