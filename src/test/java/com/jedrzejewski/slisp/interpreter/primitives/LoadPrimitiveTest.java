package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.interpreter.Interpreter;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class LoadPrimitiveTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    protected File scriptFile;

    @Before
    public void loadFile() throws Exception {
        scriptFile = temporaryFolder.newFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(scriptFile));
        out.write("(defn f [n] n)");
        out.close();
    }

    @Test
    public void testLoadingFile() throws Exception {
        Interpreter interpreter = new Interpreter();
        TestUtils.evalString("(load \"" + scriptFile.getAbsolutePath() + "\")",
                             interpreter);
        Assert.assertEquals(
                new Num(1),
                TestUtils.evalString("(f 1)", interpreter)
        );
    }
}
