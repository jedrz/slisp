package com.jedrzejewski.slisp.lispobjects;

import com.jedrzejewski.slisp.interpreter.Scope;
import java.util.List;

public class Function implements LispObject {

    private List<Sym> args;
    private LispObject body;
    private Scope scope;

    public Function(List<Sym> args, LispObject body, Scope scope) {
        this.args = args;
        this.body = body;
        this.scope = scope;
    }

    public Function(List<Sym> args, List<LispObject> bodyList, Scope scope) {
        this(args, new Sym("") /* FIXME: okropne */, scope);
        Lst wrappedBody = new Lst();
        wrappedBody.add(new Sym("do"));
        wrappedBody.addAll(bodyList);
        body = wrappedBody;
    }

    public List<Sym> getArgs() {
        return args;
    }

    public LispObject getBody() {
        return body;
    }

    public Scope getScope() {
        return scope;
    }
}
