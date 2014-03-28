package com.jedrzejewski.slisp.interpreter;

import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Sym;
import java.util.HashMap;
import java.util.Map;

public class Scope {

    private Map<Sym, LispObject> symbolTable;
    private Scope outerScope;

    public Scope(Scope outerScope) {
        symbolTable = new HashMap<>();
        this.outerScope = outerScope;
    }

    public Scope() {
        this(null);
    }

    public LispObject find(Sym sym) {
        if (symbolTable.containsKey(sym)) {
            return symbolTable.get(sym);
        } else {
            // TODO: throw exception if there is no sym in table
            return outerScope.find(sym);
        }
    }

    public LispObject find(String name) {
        return find(new Sym(name));
    }

    public Scope put(Sym sym, LispObject object) {
        symbolTable.put(sym, object);
        return this;
    }

    public Scope put(String name, LispObject object) {
        return put(new Sym(name), object);
    }
}
