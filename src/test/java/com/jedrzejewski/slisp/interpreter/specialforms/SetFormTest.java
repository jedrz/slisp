package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.Interpreter;
import com.jedrzejewski.slisp.lexer.Lexer;
import com.jedrzejewski.slisp.parser.Parser;
import com.jedrzejewski.slisp.parser.lispobjects.Num;
import com.jedrzejewski.slisp.parser.lispobjects.Symbol;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SetFormTest {

    @Test
    public void testSet() throws Exception {
        Interpreter interpreter = new Interpreter();

        Lexer lexer = new Lexer("(set! var (+ 1 2))");
        Parser parser = new Parser(lexer);
        org.junit.Assert.assertEquals(
                new Symbol("var"),
                interpreter.eval(parser.parse())
        );

        lexer = new Lexer("var");
        parser = new Parser(lexer);
        org.junit.Assert.assertEquals(
                new Num(3),
                interpreter.eval(parser.parse())
        );
    }
}
