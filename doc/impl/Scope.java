public class Scope {
    
    private Map<Sym, LispObject> symbolTable;
    private Scope outerScope;

    public Scope(Scope outerScope);
    public LispObject find(Sym sym);
    public Scope put(Sym sym, LispObject object);
}
