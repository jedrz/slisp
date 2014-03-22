package com.jedrzejewski.slisp.interpreter;

import com.jedrzejewski.slisp.lexer.Lexer;
import com.jedrzejewski.slisp.parser.Parser;
import com.jedrzejewski.slisp.parser.lispobjects.LispObject;
import com.jedrzejewski.slisp.parser.lispobjects.Num;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class InterpreterTest {

    @Test
    public void testEval() throws Exception {
        Interpreter interpreter = new Interpreter();
        Lexer lexer = new Lexer("(+ 1 (+ 2 0 (+ 0 0)) (+ 3 4 5))");
        Parser parser = new Parser(lexer);
        LispObject object = parser.parse();
        org.junit.Assert.assertEquals(new Num(15), interpreter.eval(object));
    }
}
