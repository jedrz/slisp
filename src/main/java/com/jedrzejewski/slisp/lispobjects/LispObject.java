package com.jedrzejewski.slisp.lispobjects;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;

public interface LispObject {
    /**
     * Evaluates a lisp object.
     * @param scope current scope
     * @return the result of evaluation
     * @throws InterpreterException
     */
    LispObject eval(Scope scope) throws InterpreterException;
}
