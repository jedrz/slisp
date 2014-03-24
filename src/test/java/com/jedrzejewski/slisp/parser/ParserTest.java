package com.jedrzejewski.slisp.parser;

import com.jedrzejewski.slisp.lexer.Lexer;
import com.jedrzejewski.slisp.parser.lispobjects.Lst;
import com.jedrzejewski.slisp.parser.lispobjects.Num;
import com.jedrzejewski.slisp.parser.lispobjects.Sym;
import org.junit.Test;

public class ParserTest {

    @Test
    public void testParse() {
        Lexer lexer = new Lexer("(fn 1 (pred 2 (add-one 3)) 4)");
        Parser parser = new Parser(lexer);
        Lst lst = (Lst) parser.parse();
        org.junit.Assert.assertEquals(new Sym("fn"), lst.get(0));
        org.junit.Assert.assertEquals(new Num(1), lst.get(1));
        Lst predLst = (Lst) lst.get(2);
        org.junit.Assert.assertEquals(new Sym("pred"), predLst.get(0));
        org.junit.Assert.assertEquals(new Num(2), predLst.get(1));
        Lst addOneLst = (Lst) predLst.get(2);
        org.junit.Assert.assertEquals(new Sym("add-one"), addOneLst.get(0));
        org.junit.Assert.assertEquals(new Num(3), addOneLst.get(1));
        org.junit.Assert.assertEquals(new Num(4), lst.get(3));
    }
}
