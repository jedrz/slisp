package com.jedrzejewski.slisp.parser;

import com.jedrzejewski.slisp.BaseException;
import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lexer.Lexer;
import com.jedrzejewski.slisp.lispobjects.Lst;
import com.jedrzejewski.slisp.lispobjects.Num;
import com.jedrzejewski.slisp.lispobjects.Str;
import com.jedrzejewski.slisp.lispobjects.Sym;
import com.jedrzejewski.slisp.lispobjects.Vec;
import com.jedrzejewski.slisp.parser.exceptions.MissingCloseBracketException;
import com.jedrzejewski.slisp.parser.exceptions.MissingCloseParenException;
import com.jedrzejewski.slisp.parser.exceptions.UnexpectedCloseBracketException;
import com.jedrzejewski.slisp.parser.exceptions.UnexpectedCloseParenException;
import com.jedrzejewski.slisp.parser.exceptions.UnexpectedEOFException;
import org.junit.Assert;
import org.junit.Test;

public class ParserTest {

    @Test
    public void testParse() {
        Lst lst = (Lst) TestUtils.parseString("(fn 1 (pred 2 (add-one 3)) 4)");
        Assert.assertEquals(new Sym("fn"), lst.get(0));
        Assert.assertEquals(new Num(1), lst.get(1));
        Lst predLst = (Lst) lst.get(2);
        Assert.assertEquals(new Sym("pred"), predLst.get(0));
        Assert.assertEquals(new Num(2), predLst.get(1));
        Lst addOneLst = (Lst) predLst.get(2);
        Assert.assertEquals(new Sym("add-one"), addOneLst.get(0));
        Assert.assertEquals(new Num(3), addOneLst.get(1));
        Assert.assertEquals(new Num(4), lst.get(3));
    }

    @Test
    public void testBrackets() {
        Vec vec = (Vec) TestUtils.parseString("[1 [a 2]]");
        Assert.assertEquals(new Num(1), vec.get(0));
        Vec innerVec = (Vec) vec.get(1);
        Assert.assertEquals(new Sym("a"), innerVec.get(0));
        Assert.assertEquals(new Num(2), innerVec.get(1));
    }

    @Test
    public void testQuote() {
        Assert.assertEquals(
                TestUtils.parseString("(quote (1 2))"),
                TestUtils.parseString("'(1 2)")
        );
    }

    @Test
    public void testNumbers() {
        Assert.assertEquals(
                new Num(1.1),
                TestUtils.parseString("1.1")
        );
        Assert.assertEquals(
                new Num(0.1),
                TestUtils.parseString(".1")
        );
        Assert.assertEquals(
                new Num(1),
                TestUtils.parseString("1.")
        );
        Assert.assertEquals(
                new Num(0),
                TestUtils.parseString("00.")
        );
    }

    @Test
    public void testStr() {
        Assert.assertEquals(
                new Str("str"),
                TestUtils.parseString("\"str\"")
        );
    }

    @Test(expected = MissingCloseParenException.class)
    public void testMissingCloseParen() throws Exception {
        parseWithException("(");
    }

    @Test(expected = MissingCloseBracketException.class)
    public void testMissingCloseBracket() throws Exception {
        parseWithException("[");
    }

    @Test(expected = UnexpectedCloseParenException.class)
    public void testUnexpectedCloseParen() throws Exception {
        parseWithException("())");
    }

    @Test(expected = UnexpectedCloseBracketException.class)
    public void testUnexpectedCloseBracket() throws Exception {
        parseWithException("[]]");
    }

    @Test(expected = UnexpectedEOFException.class)
    public void testUnexpectedEOF() throws Exception {
        parseWithException("'");
    }

    private static void parseWithException(String in) throws BaseException {
        Parser parser = new Parser(new Lexer(in));
        while (parser.parse() != null) {
        }
    }
}
