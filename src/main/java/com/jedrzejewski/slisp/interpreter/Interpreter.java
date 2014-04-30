package com.jedrzejewski.slisp.interpreter;

import com.jedrzejewski.slisp.BaseException;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.primitives.AddPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.ApplyPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.CallablePredicatePrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.ConcatPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.CountPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.DividePrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.EqualPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.EqualsPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.EvalPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.FirstPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.GreaterEqualPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.GreaterPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.LessEqualPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.LessPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.ListPredicatePrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.ListPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.LoadPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.MinusPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.ModuloPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.MultiplyPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.NumberPredicatePrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.PrintPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.ReadPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.RestPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.StrPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.StringPredicatePrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.SubsPrimitive;
import com.jedrzejewski.slisp.interpreter.primitives.SymbolPredicatePrimitive;
import com.jedrzejewski.slisp.interpreter.specialforms.DefmacroForm;
import com.jedrzejewski.slisp.interpreter.specialforms.DefnForm;
import com.jedrzejewski.slisp.interpreter.specialforms.DoForm;
import com.jedrzejewski.slisp.interpreter.specialforms.FnForm;
import com.jedrzejewski.slisp.interpreter.specialforms.IfForm;
import com.jedrzejewski.slisp.interpreter.specialforms.LetForm;
import com.jedrzejewski.slisp.interpreter.specialforms.QuoteForm;
import com.jedrzejewski.slisp.interpreter.specialforms.SetForm;
import com.jedrzejewski.slisp.interpreter.specialforms.WhileForm;
import com.jedrzejewski.slisp.lexer.Lexer;
import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Nil;
import com.jedrzejewski.slisp.parser.Parser;
import java.io.InputStreamReader;
import java.io.Reader;

public class Interpreter {

    private Scope globalScope;

    public Interpreter() {
        setUpGlobalScope();
        loadBuiltins();
    }

    private void setUpGlobalScope() {
        globalScope = new Scope();

        globalScope
                // Math primitives
                .put("+", new AddPrimitive())
                .put("-", new MinusPrimitive())
                .put("*", new MultiplyPrimitive())
                .put("/", new DividePrimitive())
                .put("%", new ModuloPrimitive())
                .put("=", new EqualPrimitive())
                .put("<", new LessPrimitive())
                .put("<=", new LessEqualPrimitive())
                .put(">", new GreaterPrimitive())
                .put(">=", new GreaterEqualPrimitive())

                // List primitves
                .put("list", new ListPrimitive())
                .put("concat", new ConcatPrimitive())
                .put("first", new FirstPrimitive())
                .put("rest", new RestPrimitive())

                // String primitives
                .put("str", new StrPrimitive())
                .put("subs", new SubsPrimitive())

                // Primitives for multiple types
                .put("equals", new EqualsPrimitive())
                .put("count", new CountPrimitive())

                // Types predicates
                .put("symbol?", new SymbolPredicatePrimitive())
                .put("list?", new ListPredicatePrimitive())
                .put("number?", new NumberPredicatePrimitive())
                .put("string?", new StringPredicatePrimitive())
                .put("callable?", new CallablePredicatePrimitive())

                // Misc
                .put("eval", new EvalPrimitive())
                .put("apply", new ApplyPrimitive())

                // Print, read
                .put("print", new PrintPrimitive())
                .put("read", new ReadPrimitive())
                .put("load", new LoadPrimitive())

                // Special forms
                .put("set!", new SetForm())
                .put("fn", new FnForm())
                .put("defn", new DefnForm())
                .put("defmacro", new DefmacroForm())
                .put("do", new DoForm())
                .put("if", new IfForm())
                .put("while", new WhileForm())
                .put("let", new LetForm())

                // Quotations
                .put("quote", new QuoteForm())

                // nil, true, false
                .put("nil", new Nil())
                .put("true", new Bool(true))
                .put("false", new Bool(false));
    }

    private void loadBuiltins() {
        Reader builtinsReader = new InputStreamReader(
                getClass().getResourceAsStream("/builtins.clj")
        );
        Lexer lexer = new Lexer(builtinsReader);
        Parser parser = new Parser(lexer);
        LispObject code;
        try {
            while ((code = parser.parse()) != null) {
                eval(code);
            }
        } catch (BaseException e) {
            System.out.println(e.getFullMessage());
            throw new RuntimeException("Built-in functions are broken!");
        }
    }

    public LispObject eval(LispObject code) throws InterpreterException {
        return code.eval(globalScope);
    }
}
