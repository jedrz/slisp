package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Str;
import java.util.List;

public class StrPrimitive extends Primitive {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope) {
        String joinResult = args.stream()
                                .map(arg -> {
                                    if (arg instanceof Str) {
                                        Str str = (Str) arg;
                                        return str.getString();
                                    } else {
                                        return arg.toString();
                                    }
                                })
                                .reduce((acc, arg) -> acc + arg)
                                .get();
        return new Str(joinResult);
    }
}
