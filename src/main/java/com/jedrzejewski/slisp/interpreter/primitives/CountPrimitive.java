package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Lst;
import com.jedrzejewski.slisp.lispobjects.Num;
import com.jedrzejewski.slisp.lispobjects.Str;
import java.util.List;

public class CountPrimitive extends Primitive {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope sc) {
        if (args.size() == 1) {
            LispObject obj = args.get(0);
            if (obj instanceof Lst) {
                Lst lst = (Lst) obj;
                return new Num(lst.size());
            } else if (obj instanceof Str) {
                Str str = (Str) obj;
                return new Num(str.getString().length());
            } else {
                // TODO: invalid type.
            }
        }
        // TODO: throw an exception when more than one arg are passed.
        return new Num(-1);
    }
}
