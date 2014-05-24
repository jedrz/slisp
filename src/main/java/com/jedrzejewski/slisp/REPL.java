package com.jedrzejewski.slisp;

import com.jedrzejewski.slisp.interpreter.Interpreter;
import com.jedrzejewski.slisp.lexer.Lexer;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.parser.Parser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class REPL {

    private Interpreter interpreter = new Interpreter();

    public void run() {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            System.out.print("> ");
            String input = "";
            try {
                input = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Lexer lexer = new Lexer(input);
                Parser parser = new Parser(lexer);
                LispObject lispObject = parser.parse();
                if (lispObject != null) {
                    LispObject result = interpreter.eval(lispObject);
                    System.out.println(result);
                }
            } catch (BaseException e) {
                System.out.println(e.getFullMessage());
            } catch (Throwable e) {
                System.out.println("Unhandled error");
            }
        }
    }
}
