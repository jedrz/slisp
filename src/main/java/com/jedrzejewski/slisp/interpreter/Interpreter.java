package com.jedrzejewski.slisp.interpreter;

import com.jedrzejewski.slisp.interpreter.primitives.PrimitiveAdd;
import com.jedrzejewski.slisp.interpreter.primitives.PrimitiveMinus;
import com.jedrzejewski.slisp.parser.Expression;
import com.jedrzejewski.slisp.parser.LispObject;
import com.jedrzejewski.slisp.parser.Number;
import com.jedrzejewski.slisp.parser.Symbol;
import java.util.LinkedList;
import java.util.List;

public class Interpreter {

    private Scope globalScope;

    public Interpreter() {
        globalScope = new Scope();
        globalScope
                .put("+", new PrimitiveAdd())
                .put("-", new PrimitiveMinus());
    }

    public LispObject eval(LispObject code) {
        return eval(code, globalScope);
    }

    private LispObject eval(LispObject code, Scope scope) {
        if (code instanceof Number) {
            return code;
        } else if (code instanceof Symbol) {
            Symbol symbol = (Symbol) code;
            return scope.find(symbol);
        } else if (code instanceof Expression) {
            Expression exp = (Expression) code;
            LispObject first = exp.get(0);
            // TODO: obsługa specjalnych wyrażeń.
            // Wywołanie funkcji
            LispObject fn = eval(first, scope);
            if (fn instanceof Primitive) {
                Primitive primitive = (Primitive) fn;
                List<LispObject> args = new LinkedList<>();
                for (LispObject object : exp.subList(1, exp.size())) {
                    args.add(eval(object, scope));
                }
                // TODO: obsługa liczby argumentów
                return primitive.call(args);
            }
            // TODO: obsługa lambd
            // TODO: jeśli fn nie jest funkcją
        }
        return null;
    }
}
