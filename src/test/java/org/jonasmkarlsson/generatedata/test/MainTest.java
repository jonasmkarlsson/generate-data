package org.jonasmkarlsson.generatedata.test;

import static org.junit.Assert.assertEquals;

import org.jonasmkarlsson.generatedata.Launcher;
import org.jonasmkarlsson.generatedata.Main;
import org.junit.Test;

public class MainTest extends AbstractCliTest {

    @Test
    public void testRunWithNullAsArgument() {
        new Main();
        Main.main(null);
        assertEquals(gnu, outContent.toString());
    }

    @Test
    public void testRunWithEmptyArguments() {
        String[] args = { "" };
        new Launcher().run(args);
        assertEquals(gnu, outContent.toString());
    }

}
