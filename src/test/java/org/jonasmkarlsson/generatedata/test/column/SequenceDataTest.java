package org.jonasmkarlsson.generatedata.test.column;

import static org.junit.Assert.assertEquals;

import org.jonasmkarlsson.generatedata.column.SequenceData;
import org.junit.Test;

public class SequenceDataTest {

    final String regExp = ".*";
    final String characters = "abc";

    @Test
    public void testSequenceDataDefaultConstructorWith3Parameters() {
        final int numberOfTimes = 2;
        final String toString = "SequenceData [regexp=" + regExp + ", characters=" + characters + ", numberOfTimes=" + numberOfTimes + "]";
        SequenceData sequenceData = new SequenceData(regExp, characters, numberOfTimes);
        assertEquals(regExp, sequenceData.getRegexp());
        assertEquals(characters, sequenceData.getCharacters());
        assertEquals(numberOfTimes, sequenceData.getNumberOfTimes());
        assertEquals(toString, sequenceData.toString());
    }
}
