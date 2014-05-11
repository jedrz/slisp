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

    public LispObject eval(LispObject code) throws InterpreterException {
        return code.eval(globalScope);
    }
}
