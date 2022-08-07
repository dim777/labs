package ru.ineb.labprimitives;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.lang.instrument.Instrumentation;

/**
 * byte (1 byte, 8 bit)
 * short (2 byte, 16 bit)
 * int (4 byte, 32 bit)
 * long (8 byte, 64 bit)
 * float (4 byte)
 * double (8 byte)
 * char (Unicode, 2 byte)
 * boolean (1 byte)
 */
public class LabPrimitivesApplicationTests {
    private static final Logger LOGGER = LogManager.getLogger(LabPrimitivesApplicationTests.class);

    /**
     * same objects in memory
     */
    @Test
    public void stringTestA() {
        String a = "a";
        String a1 = "a";

        int aHc = a.hashCode();
        int aaHc = a1.hashCode();

        Assert.assertEquals(aHc, aaHc);
    }

    @Test
    public void stringTestAA() {
        String a = "a";
        String aa = "aa";

        int aHc = a.hashCode();
        int aaHc = aa.hashCode();

        Assert.assertNotEquals(aHc, aaHc);
    }

    @Test
    public void objTest1() {
        Tst tst0 = new Tst();
        Tst tst1 = new Tst();

        int tst0Hc = tst0.hashCode();
        int tst1Hc = tst1.hashCode();

        Assert.assertNotEquals(tst0Hc, tst1Hc);
    }

    static class Tst {

    }

    static class Tst1 {
        int i = 0;
    }
}
