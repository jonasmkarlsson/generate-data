package org.jonasmkarlsson.generatedata.column;

public class SequenceData {

    private String regexp = new String("");
    private String characters = new String("");
    private int numberOfTimes = 0;

    public SequenceData(final String regExp, final String characters, final int numberOfTimes) {
        this.regexp = regExp;
        this.characters = characters;
        this.numberOfTimes = numberOfTimes;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SequenceData [regexp=" + regexp + ", characters=" + characters + ", numberOfTimes=" + numberOfTimes + "]";
    }

    /**
     * @return the regexp
     */
    public String getRegexp() {
        return regexp;
    }

    /**
     * @return the characters
     */
    public String getCharacters() {
        return characters;
    }

    /**
     * @return the numberOfTimes
     */
    public int getNumberOfTimes() {
        return numberOfTimes;
    }

}
