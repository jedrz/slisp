package com.jedrzejewski.slisp.interpreter;

import com.jedrzejewski.slisp.interpreter.primitives.Primitive;
import com.jedrzejewski.slisp.interpreter.primitives.PrimitiveAdd;
import com.jedrzejewski.slisp.interpreter.primitives.PrimitiveDivide;
import com.jedrzejewski.slisp.interpreter.primitives.PrimitiveMinus;
import com.jedrzejewski.slisp.interpreter.primitives.PrimitiveMultiply;
import com.jedrzejewski.slisp.interpreter.specialforms.SetForm;
import com.jedrzejewski.slisp.interpreter.specialforms.SpecialForm;
import com.jedrzejewski.slisp.parser.lispobjects.Expression;
import com.jedrzejewski.slisp.parser.lispobjects.LispObject;
import com.jedrzejewski.slisp.parser.lispobjects.Num;
import com.jedrzejewski.slisp.parser.lispobjects.Symbol;
import java.util.LinkedList;
import java.util.List;

public class Interpreter {

    private Scope globalScope;

    public Interpreter() {
        globalScope = new Scope();

        // Prymitywy
        globalScope
                .put("+", new PrimitiveAdd())
                .put("-", new PrimitiveMinus())
                .put("*", new PrimitiveMultiply())
                .put("/", new PrimitiveDivide());

        // Specjalne formy
        globalScope
                .put("set!", new SetForm());
    }

    public LispObject eval(LispObject code) {
        return eval(code, globalScope);
    }

    private LispObject eval(LispObject code, Scope scope) {
        if (code instanceof Num) {
            return code;
        } else if (code instanceof Symbol) {
            Symbol symbol = (Symbol) code;
            return scope.find(symbol);
        } else if (code instanceof Expression) {
            Expression exp = (Expression) code;
            LispObject first = exp.get(0);
            LispObject fn = eval(first, scope);
            if (fn instanceof SpecialForm) {
                SpecialForm specialForm = (SpecialForm) fn;
                List<LispObject> args = exp.subList(1, exp.size());
                return specialForm.call(args, this::eval, scope);
            }
            else if (fn instanceof Primitive) {
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
