public class Function implements LispObject {

    private List<Sym> args;
    private LispObject body;
    private Scope scope;

    public Function(List<Sym> args, LispObject body, Scope scope);
}
