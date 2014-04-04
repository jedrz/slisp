package com.jedrzejewski.slisp;

import org.junit.Assert;
import org.junit.Test;

public class BaseExceptionTest {

    @Test
    public void testMessageWithoutPrefix() {
        NoPrefixException e = new NoPrefixException("message");
        Assert.assertEquals(
                "message",
                e.getFullMessage()
        );
    }

    @Test
    public void testMessageWithPrefix() {
        PrefixException e = new PrefixException("message");
        Assert.assertEquals(
                "prefix: message",
                e.getFullMessage()
        );
    }

    @Test
    public void testAutoMessage() {
        AutoMessageException e = new AutoMessageException();
        Assert.assertEquals(
                "auto message",
                e.getMessage()
        );
    }

    private class NoPrefixException extends BaseException {

        public NoPrefixException(String message) {
            super(null, message);
        }
    }

    private class PrefixException extends BaseException {

        public PrefixException(String message) {
            super("prefix", message);
        }
    }

    private class AutoMessageException extends BaseException {
    }
}
