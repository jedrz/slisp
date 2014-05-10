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

    /**
     * Looks for the value of the given symbol in the symbol table
     * or parent scope if symbol is not found.
     * @param sym symbol for which the binded value should be found
     * @return the value of the symbol
     * @throws SymbolUndefinedException if symbol is undefined
     */
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

    /**
     * Put pair - symbol and value in scope possibly overriding
     * existing value of the symbol.
     * @param sym symbol
     * @param object the value of the symbol
     * @return scope with given pair
     */
    public Scope put(Sym sym, LispObject object) {
        symbolTable.put(sym, object);
        return this;
    }

    public Scope put(String name, LispObject object) {
        return put(new Sym(name), object);
    }
}
