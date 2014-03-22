package com.jedrzejewski.slisp.interpreter;

import com.jedrzejewski.slisp.parser.LispObject;
import com.jedrzejewski.slisp.parser.Symbol;
import java.util.HashMap;
import java.util.Map;

public class Scope {

    private Map<Symbol, LispObject> symbolTable;
    private Scope outerScope;

    public Scope(Scope outerScope) {
        symbolTable = new HashMap<>();
        this.outerScope = outerScope;
    }

    public Scope() {
        this(null);
    }

    public LispObject find(Symbol symbol) {
        if (symbolTable.containsKey(symbol)) {
            return symbolTable.get(symbol);
        } else {
            // TODO: throw exception if there is no symbol in table
            return outerScope.find(symbol);
        }
    }

    public LispObject find(String name) {
        return find(new Symbol(name));
    }

    public Scope put(Symbol symbol, LispObject object) {
        symbolTable.put(symbol, object);
        return this;
    }

    public Scope put(String name, LispObject object) {
        return put(new Symbol(name), object);
    }
}
