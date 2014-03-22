package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.parser.LispObject;
import java.util.List;

public interface Primitive extends LispObject {
    public LispObject call(List<LispObject> args);
}
