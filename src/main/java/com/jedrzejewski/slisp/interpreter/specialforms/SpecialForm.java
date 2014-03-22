package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.Evaluater;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.parser.lispobjects.LispObject;
import java.util.List;

public interface SpecialForm extends LispObject {
    LispObject call(List<LispObject> args, Evaluater evaluater, Scope scope);
}
