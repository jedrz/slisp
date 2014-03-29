package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Lst;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.List;

public class LengthPrimitive implements Primitive {

    @Override
    public LispObject call(List<LispObject> args) {
        if (args.size() == 1) {
            LispObject obj = args.get(0);
            if (obj instanceof Lst) {
                Lst lst = (Lst) obj;
                return new Num(lst.size());
            } // TODO: add string handler.
        }
        // TODO: throw an exception when more than one arg are passed.
        return new Num(-1);
    }
}
