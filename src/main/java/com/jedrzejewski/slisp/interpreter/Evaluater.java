package com.jedrzejewski.slisp.interpreter;

import com.jedrzejewski.slisp.parser.lispobjects.LispObject;

public interface Evaluater {
    LispObject eval(LispObject code, Scope scope);
}
