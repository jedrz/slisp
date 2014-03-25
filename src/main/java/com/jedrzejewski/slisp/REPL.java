package com.jedrzejewski.slisp;

import com.jedrzejewski.slisp.interpreter.Function;
import com.jedrzejewski.slisp.interpreter.Interpreter;
import com.jedrzejewski.slisp.lexer.Lexer;
import com.jedrzejewski.slisp.parser.Parser;
import com.jedrzejewski.slisp.parser.lispobjects.LispObject;
import com.jedrzejewski.slisp.parser.lispobjects.Num;
import com.jedrzejewski.slisp.parser.lispobjects.Sym;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class REPL {

    private Interpreter interpreter;
    private Map<Class<? extends LispObject>, Printer> map;

    public REPL() {
        interpreter = new Interpreter();
        map = new HashMap<>();
        map.put(Sym.class, new SymPrinter());
        map.put(Num.class, new NumPrinter());
        map.put(Function.class, new FunctionPrinter());
    }

    public void run() {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            System.out.println(">>> ");
            String input = "";
            try {
                input = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Lexer lexer = new Lexer(input);
            Parser parser = new Parser(lexer);
            LispObject lispObject = parser.parse();
            LispObject result = interpreter.eval(lispObject);
            Printer printer = map.get(result.getClass());
            System.out.println(printer.print(result));
        }
    }

    private interface Printer {
        String print(LispObject object);
    }

    private class SymPrinter implements Printer {

        @Override
        public String print(LispObject object) {
            Sym sym = (Sym) object;
            return sym.getName();
        }
    }

    private class NumPrinter implements Printer {

        @Override
        public String print(LispObject object) {
            Num num = (Num) object;
            return num.getValue().toString();
        }
    }

    private class FunctionPrinter implements Printer {

        @Override
        public String print(LispObject object) {
            return "fn form";
        }
    }
}
