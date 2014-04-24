package com.jedrzejewski.slisp.interpreter;

import com.jedrzejewski.slisp.interpreter.exceptions.SymbolUndefinedException;
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

    public LispObject find(Sym sym) throws SymbolUndefinedException {
        if (symbolTable.containsKey(sym)) {
            return symbolTable.get(sym);
        } else if (outerScope == null) {
            throw new SymbolUndefinedException(sym.getName());
        } else {
            return outerScope.find(sym);
        }
    }

    public LispObject find(String name) throws SymbolUndefinedException {
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
