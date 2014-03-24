package com.jedrzejewski.slisp;

import com.jedrzejewski.slisp.interpreter.Interpreter;
import com.jedrzejewski.slisp.lexer.Lexer;
import com.jedrzejewski.slisp.parser.Parser;
import com.jedrzejewski.slisp.parser.lispobjects.LispObject;

public class TestUtils {

    public static LispObject parseString(String input) {
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);
        return parser.parse();
    }

    public static LispObject evalString(String input, Interpreter interpreter) {
        return interpreter.eval(parseString(input));
    }

    public static LispObject evalString(String input) {
        Interpreter interpreter = new Interpreter();
        return evalString(input, interpreter);
    }
}
