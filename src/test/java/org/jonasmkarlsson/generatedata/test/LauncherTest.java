package org.jonasmkarlsson.generatedata.test;

import static org.junit.Assert.assertEquals;

import org.jonasmkarlsson.generatedata.Constants;
import org.jonasmkarlsson.generatedata.Launcher;
import org.junit.Test;

public class LauncherTest extends AbstractCliTest {

    String[] lines = new String[0];

    @Test
    public void testRunWithParseException() {
        String[] args = { "-parse" };
        new Launcher().run(args);
        assertEquals("Encountered exception while parsing using GnuParser: Unrecognized option: -parse" + separator, outContent.toString());
    }

    @Test
    public void testRunWithShortVersionArgument() {
        String[] args = { "-v" };
        new Launcher().run(args);
        assertEquals(new Launcher().getApplication() + separator, outContent.toString());
    }

    @Test
    public void testRunWithLongVersionArgument() {
        String[] args = { "-version" };
        new Launcher().run(args);
        assertEquals(new Launcher().getApplication() + separator, outContent.toString());
    }

    @Test
    public void testRunWithShortArgument() {
        String columns = "firstname";
        String[] args = { "-f", columns };
        new Launcher().run(args);
        checkNumberOfLinesAndColumns(Constants.DEFAULT_NUMBER_OF_LINES, columns.split(Constants.DEFAULT_DELIMITER).length, Constants.DEFAULT_DELIMITER);
    }

    @Test
    public void testRunWithLongArgument() {
        String columns = "firstname";
        String[] args = { "-field", columns };
        new Launcher().run(args);
        checkNumberOfLinesAndColumns(Constants.DEFAULT_NUMBER_OF_LINES, columns.split(Constants.DEFAULT_DELIMITER).length, Constants.DEFAULT_DELIMITER);
    }

    @Test
    public void testRunWithColumnFirstnameAndLastname() {
        String columns = "firstname, lastname";
        String[] args = { "-field", columns };
        new Launcher().run(args);
        checkNumberOfLinesAndColumns(Constants.DEFAULT_NUMBER_OF_LINES, columns.split(Constants.DEFAULT_DELIMITER).length, Constants.DEFAULT_DELIMITER);
    }

    @Test
    public void testRunWithColumnsFirstnameAndLastnameAndNumberOfLines() {
        int numberOfLines = 5;
        String columns = "firstname,lastname";
        String[] args = { "-field", columns, "-n", Integer.toString(numberOfLines) };
        new Launcher().run(args);
        checkNumberOfLinesAndColumns(numberOfLines, columns.split(Constants.DEFAULT_DELIMITER).length, Constants.DEFAULT_DELIMITER);
    }

    @Test
    public void testRunWithFirstnameLastnameAsFieldArgumentAndWrongSyntaxOfNoOfLines() {
        String noNumber = "noNumber";
        String columns = "firstname,lastname,street";
        String[] args = { "-field", columns, "-n", noNumber };
        new Launcher().run(args);
        checkNumberOfLinesAndColumns(Constants.DEFAULT_NUMBER_OF_LINES, columns.split(Constants.DEFAULT_DELIMITER).length, Constants.DEFAULT_DELIMITER);
    }

    @Test
    public void testRunWithOnlyNumberOfLines() {
        String[] args = { "-n", Integer.toString(Constants.DEFAULT_NUMBER_OF_LINES) };
        new Launcher().run(args);
        assertEquals(gnu, outContent.toString());
    }

    @Test
    public void testRunWithSequence() {
        String parameter = "123-\\(123\\)";
        String expected = "123-(123)";
        String columns = "sequence(" + parameter + ")";
        int numberOfLines = 1;
        String[] args = { "-f", columns, "-n", Integer.toString(numberOfLines) };
        new Launcher().run(args);
        assertEquals(expected + separator, outContent.toString());
        checkNumberOfLinesAndColumns(numberOfLines, columns.split(Constants.DEFAULT_DELIMITER).length, Constants.DEFAULT_DELIMITER);
    }

    private void checkNumberOfLinesAndColumns(final int numberOfLines, final int numberOfColumns, final String delimiter) {
        lines = outContent.toString().split(separator);
        checkNumberOfLines(numberOfLines);
        checkNumberOfColumns(numberOfColumns, delimiter);
    }

    private void checkNumberOfLines(final int numberOfLines) {
        assertEquals(numberOfLines, lines.length);
    }

    private void checkNumberOfColumns(final int numberOfColumns, final String delimiter) {
        for (String line : lines) {
            assertEquals(numberOfColumns, line.split(delimiter).length);
        }
    }

}
