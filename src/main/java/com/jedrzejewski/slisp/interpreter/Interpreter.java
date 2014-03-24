package com.jedrzejewski.slisp.interpreter;

import com.jedrzejewski.slisp.interpreter.primitives.AddPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.DividePrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.MinusPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.MultiplyPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.Primitive;
import com.jedrzejewski.slisp.interpreter.specialforms.SetForm;
import com.jedrzejewski.slisp.interpreter.specialforms.SpecialForm;
import com.jedrzejewski.slisp.parser.lispobjects.LispObject;
import com.jedrzejewski.slisp.parser.lispobjects.Lst;
import com.jedrzejewski.slisp.parser.lispobjects.Num;
import com.jedrzejewski.slisp.parser.lispobjects.Sym;
import java.util.LinkedList;
import java.util.List;

public class Interpreter {

    private Scope globalScope;

    public Interpreter() {
        globalScope = new Scope();

        // Prymitywy
        globalScope
                .put("+", new AddPrimitive())
                .put("-", new MinusPrimitive())
                .put("*", new MultiplyPrimitive())
                .put("/", new DividePrimitive());

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
        } else if (code instanceof Sym) {
            Sym sym = (Sym) code;
            return scope.find(sym);
        } else if (code instanceof Lst) {
            Lst lst = (Lst) code;
            LispObject first = lst.get(0);
            LispObject fn = eval(first, scope);
            if (fn instanceof SpecialForm) {
                SpecialForm specialForm = (SpecialForm) fn;
                List<LispObject> args = lst.subList(1, lst.size());
                Evaluator evaluator = this.new Evaluator(scope);
                return specialForm.call(args, evaluator);
            }
            else if (fn instanceof Primitive) {
                Primitive primitive = (Primitive) fn;
                List<LispObject> args = new LinkedList<>();
                for (LispObject object : lst.subList(1, lst.size())) {
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

    public class Evaluator {

        private Scope scope;

        public Evaluator(Scope scope) {
            this.scope = scope;
        }

        public LispObject eval(LispObject code) {
            return Interpreter.this.eval(code, scope);
        }

        public Scope getScope() {
            return scope;
        }
    }

}
