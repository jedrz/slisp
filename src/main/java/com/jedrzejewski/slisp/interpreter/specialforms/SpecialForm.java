package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.Interpreter;
import com.jedrzejewski.slisp.parser.lispobjects.LispObject;
import java.util.List;

public interface SpecialForm extends LispObject {
    LispObject call(List<LispObject> args, Interpreter.Evaluater evaluater);
}
