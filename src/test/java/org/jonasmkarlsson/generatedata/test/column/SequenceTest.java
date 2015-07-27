package org.jonasmkarlsson.generatedata.test.column;

import static org.junit.Assert.assertEquals;

import org.jonasmkarlsson.generatedata.column.Sequence;
import org.junit.Test;

public class SequenceTest {

    @Test
    public void testGenerateSimpleSequence() {
        String parameter = "012-\\(345\\)678";
        String expected = "012-(345)678";
        Sequence sequencer = new Sequence(parameter);
        String generatedValue = sequencer.generate();
        assertEquals(expected, generatedValue);
    }

    @Test
    public void testGenerateLessComplexSequence() {
        String parameter = "0[9][2]-345678";
        String expected = "092-345678";
        Sequence sequencer = new Sequence(parameter);
        String generatedValue = sequencer.generate();
        assertEquals(expected, generatedValue);
    }

    @Test
    public void testGenerateLessComplexSequenceWitEscapeSequence() {
        String parameter = "\\[2\\]";
        String expected = "[2]";
        Sequence sequencer = new Sequence(parameter);
        String generatedValue = sequencer.generate();
        assertEquals(expected, generatedValue);
    }

    @Test
    public void testGenerateComplexSequence() {
        String parameter = "[1]{10}";
        String expected = "1111111111";
        Sequence sequencer = new Sequence(parameter);
        String generatedValue = sequencer.generate();
        assertEquals(expected, generatedValue);
    }

    @Test
    public void testGenerateComplexSequence2() {
        int expectedLength = 10;
        String parameter = "[0-9]{" + Integer.toString(expectedLength) + "}";
        Sequence sequencer = new Sequence(parameter);
        String generatedValue = sequencer.generate();
        assertEquals(expectedLength, generatedValue.length());
    }

}
