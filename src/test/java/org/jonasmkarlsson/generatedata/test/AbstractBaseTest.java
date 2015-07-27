package org.jonasmkarlsson.generatedata.test;

import static org.junit.Assert.assertEquals;

public abstract class AbstractBaseTest {

    protected void checkNumberOfColumns(final int length, final String[] lines, final String delimiter) {
        for (String line : lines) {
            assertEquals(length, line.split(delimiter).length);
        }
    }
}
