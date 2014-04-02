public class Interpreter {

    public Interpreter() {
        setUpGlobalScope();
        loadBuiltins();         // Ładuje funkcje napisane w tym języku.
    }

    private void setUpGlobalScope() {
        globalScope
                .put("*", new MultiplyPrimitive())
                .put("if", new IfForm())
                // itd.
    }

    public LispObject eval(LispObject code);
    private LispObject eval(LispObject code, Scope scope);
    private LispObject evalNum(LispObject code, Scope scope);
    private LispObject evalStr(LispObject code, Scope scope);

    private LispObject evalSym(LispObject code, Scope scope) {
        Sym sym = (Sym) code;
        return scope.find(sym);
    }

    private LispObject evalLst(LispObject code, Scope scope) {
        Lst lst = (Lst) code;
        LispObject first = lst.get(0);
        LispObject fn = eval(first, scope);
        // obsługa funkcji, prymitywów oraz funkcji zdefiniowanych w języku.
    }

    // Opakowuje interpreter.
    public class Evaluator {
        private Scope scope;
        public LispObject eval(LispObject code);
        public LispObject eval(LispObject code, Scope scope);
        public Scope getScope();
}
