package com.jedrzejewski.slisp.interpreter;

import com.jedrzejewski.slisp.parser.LispObject;
import java.util.List;

public interface Primitive extends LispObject {
    public LispObject call(List<LispObject> args);
    public int minArgs();
    public boolean varArgs();
}
