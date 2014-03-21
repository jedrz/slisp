package com.jedrzejewski.slisp.parser;

import com.jedrzejewski.slisp.lexer.Lexer;
import com.jedrzejewski.slisp.lexer.Number;
import com.jedrzejewski.slisp.lexer.Symbol;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ParserTest {

    @Test
    public void testParse() {
        Lexer lexer = new Lexer("(fn 1 (pred 2 (add-one 3)) 4)");
        Parser parser = new Parser(lexer);
        List<?> exp = (List<?>) parser.parse();
        org.junit.Assert.assertEquals(new Symbol("fn"), exp.get(0));
        org.junit.Assert.assertEquals(new Number(1), exp.get(1));
        List<?> predExp = (List<?>) exp.get(2);
        org.junit.Assert.assertEquals(new Symbol("pred"), predExp.get(0));
        org.junit.Assert.assertEquals(new Number(2), predExp.get(1));
        List<?> addOneExp = (List<?>) predExp.get(2);
        org.junit.Assert.assertEquals(new Symbol("add-one"), addOneExp.get(0));
        org.junit.Assert.assertEquals(new Number(3), addOneExp.get(1));
        org.junit.Assert.assertEquals(new Number(4), exp.get(3));
    }
}
