package org.jonasmkarlsson.generatedata.test;

import static org.junit.Assert.assertEquals;

import org.jonasmkarlsson.generatedata.Constants;
import org.jonasmkarlsson.generatedata.Generate;
import org.jonasmkarlsson.generatedata.column.AbstractColumn;
import org.jonasmkarlsson.generatedata.column.Company;
import org.jonasmkarlsson.generatedata.column.Firstname;
import org.jonasmkarlsson.generatedata.column.Lastname;
import org.jonasmkarlsson.generatedata.column.Location;
import org.jonasmkarlsson.generatedata.column.Sequence;
import org.jonasmkarlsson.generatedata.column.Street;
import org.junit.Test;

public class GenerateTest extends AbstractBaseTest {

    int numberOfLines = 3;

    @Test
    public void testListWithFieldSimpleFirstname() {
        AbstractColumn[] columns = { new Firstname("") };
        String[] lines = Generate.list(columns, numberOfLines, Constants.DEFAULT_DELIMITER);
        assertEquals(numberOfLines, lines.length);
        checkNumberOfColumns(columns.length, lines, Constants.DEFAULT_DELIMITER);
    }

    @Test
    public void testListWithFieldSimpleLastname() {
        AbstractColumn[] columns = { new Lastname("") };
        String[] lines = Generate.list(columns, numberOfLines, Constants.DEFAULT_DELIMITER);
        assertEquals(numberOfLines, lines.length);
        checkNumberOfColumns(columns.length, lines, Constants.DEFAULT_DELIMITER);
    }

    @Test
    public void testListWithFieldSimpleCompany() {
        AbstractColumn[] columns = { new Company("") };
        String[] lines = Generate.list(columns, numberOfLines, Constants.DEFAULT_DELIMITER);
        assertEquals(numberOfLines, lines.length);
        checkNumberOfColumns(columns.length, lines, Constants.DEFAULT_DELIMITER);
    }

    @Test
    public void testListWithFieldSimpleStreet() {
        AbstractColumn[] columns = { new Street("") };
        String[] lines = Generate.list(columns, numberOfLines, Constants.DEFAULT_DELIMITER);
        assertEquals(numberOfLines, lines.length);
        checkNumberOfColumns(columns.length, lines, Constants.DEFAULT_DELIMITER);
    }

    @Test
    public void testListWithFieldSimpleLocation() {
        AbstractColumn[] columns = { new Location("") };
        String[] lines = Generate.list(columns, numberOfLines, Constants.DEFAULT_DELIMITER);
        assertEquals(numberOfLines, lines.length);
        checkNumberOfColumns(columns.length, lines, Constants.DEFAULT_DELIMITER);
    }

    @Test
    public void testListWithFieldSimpleSequence() {
        numberOfLines = 18;
        String sequence = "46-7[0-9]-[0-9][0-9][0-9][0-9][0-9][0-9][0-9]";
        AbstractColumn[] columns = { new Sequence(sequence) };
        String[] lines = Generate.list(columns, numberOfLines, Constants.DEFAULT_DELIMITER);
        assertEquals(numberOfLines, lines.length);
        checkNumberOfColumns(columns.length, lines, Constants.DEFAULT_DELIMITER);
    }

    @Test
    public void testListWithMultipleColumns() {
        AbstractColumn[] columns = { new Firstname(""), new Lastname(""), new Company(""), new Street(""), new Location("") };
        String[] lines = Generate.list(columns, numberOfLines, Constants.DEFAULT_DELIMITER);
        assertEquals(numberOfLines, lines.length);
        checkNumberOfColumns(columns.length, lines, Constants.DEFAULT_DELIMITER);
    }

    @Test
    public void testListWithMultipleColumnsAsStrings() throws Exception {
        String[] columns = { "FIRSTNAME", "firstname", "LastName", "COMpany", "STReeT", "locaTion" };
        String[] lines = Generate.list(columns, numberOfLines, Constants.DEFAULT_DELIMITER);
        assertEquals(numberOfLines, lines.length);
        checkNumberOfColumns(columns.length, lines, Constants.DEFAULT_DELIMITER);
    }

}
