package com.jedrzejewski.slisp.interpreter;

import com.jedrzejewski.slisp.interpreter.primitives.AddPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.DividePrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.MinusPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.MultiplyPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.Primitive;
import com.jedrzejewski.slisp.interpreter.specialforms.DoForm;
import com.jedrzejewski.slisp.interpreter.specialforms.FnForm;
import com.jedrzejewski.slisp.interpreter.specialforms.SetForm;
import com.jedrzejewski.slisp.interpreter.specialforms.SpecialForm;
import com.jedrzejewski.slisp.parser.lispobjects.LispObject;
import com.jedrzejewski.slisp.parser.lispobjects.Lst;
import com.jedrzejewski.slisp.parser.lispobjects.Num;
import com.jedrzejewski.slisp.parser.lispobjects.Sym;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Interpreter {

    private Scope globalScope;
    private Map<Class<? extends LispObject>, EvalFunction> classEvalFunctionMap;

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
                .put("set!", new SetForm())
                .put("do", new DoForm())
                .put("fn", new FnForm());

        classEvalFunctionMap = new HashMap<>();
        classEvalFunctionMap.put(Num.class, this::evalNum);
        classEvalFunctionMap.put(Sym.class, this::evalSym);
        classEvalFunctionMap.put(Lst.class, this::evalLst);
    }

    public LispObject eval(LispObject code) {
        return eval(code, globalScope);
    }

    private LispObject eval(LispObject code, Scope scope) {
        EvalFunction evalFunction = classEvalFunctionMap.get(code.getClass());
        if (evalFunction != null) {
            return evalFunction.eval(code, scope);
        } else {
            throw new RuntimeException("Unhandled code type!");
        }
    }

    private LispObject evalNum(LispObject code, Scope scope) {
        return code;
    }

    private LispObject evalSym(LispObject code, Scope scope) {
        Sym sym = (Sym) code;
        return scope.find(sym);
    }

    private LispObject evalLst(LispObject code, Scope scope) {
        Lst lst = (Lst) code;
        LispObject first = lst.get(0);
        LispObject fn = eval(first, scope);
        if (fn instanceof SpecialForm) {
            SpecialForm specialForm = (SpecialForm) fn;
            List<LispObject> args = lst.subList(1, lst.size());
            Evaluator evaluator = this.new Evaluator(scope);
            return specialForm.call(args, evaluator);
        } else if (fn instanceof Primitive) {
            Primitive primitive = (Primitive) fn;
            List<LispObject> args = new LinkedList<>();
            for (LispObject object : lst.subList(1, lst.size())) {
                args.add(eval(object, scope));
            }
            // TODO: obsługa liczby argumentów
            return primitive.call(args);
        } else if (fn instanceof Function) {
            Function function = (Function) fn;
            List<LispObject> args = new LinkedList<>();
            for (LispObject object : lst.subList(1, lst.size())) {
                args.add(eval(object, scope));
            }
            Scope wrapperScope = new Scope(function.getScope());
            List<Sym> argNames = function.getArgs();
            for (int i = 0; i < args.size(); ++i) {
                wrapperScope.put(argNames.get(i), args.get(i));
            }
            return eval(function.getBody(), wrapperScope);
        }
        // TODO: jeśli fn nie jest funkcją
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

    private interface EvalFunction {
        LispObject eval(LispObject code, Scope scope);
    }
}
