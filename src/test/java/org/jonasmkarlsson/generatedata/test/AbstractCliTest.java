package org.jonasmkarlsson.generatedata.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.jonasmkarlsson.generatedata.Constants;
import org.junit.After;
import org.junit.Before;

public abstract class AbstractCliTest extends AbstractBaseTest {

    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    protected final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    protected final String separator = System.getProperty("line.separator");

    // @formatter:off
    public final String gnu = "usage: java -jar generate-data.jar [-d <arg>] [-f <arg>] [-n <arg>] [-v]" + separator 
                + "Help GNU" + separator
                + "     -d,--delimiter <arg>   " + Constants.HELP_DELIMITER + separator
                + "     -f,--field <arg>       " + Constants.HELP_FIELDS + separator 
                + "     -n,--lines <arg>       " + Constants.HELP_LINES + separator
                + "     -v,--version           " + Constants.HELP_VERSION + separator 
                + "End of GNU Help" + separator;
    // @formatter:on

    /**
     * Don't write anything to System.out when doing tests against outContent, because outContent will catch every System.out print command.
     */
    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void tearDown() {
        System.setOut(null);
        System.setErr(null);
    }

}
