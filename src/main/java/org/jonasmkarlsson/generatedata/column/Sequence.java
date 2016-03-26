package org.jonasmkarlsson.generatedata.column;

/**
 * <p>
 * Responsible for generating a value containing a defined sequence of characters, specified in the parameter property. Allowed characters are specified in the
 * property AVAILABLE_CHARACTER.
 * </p>
 * 
 * <p>
 * Hard brackets []: Regression expression that defines what kind of value are allowed into this position.<br/>
 * Curly bracket {}: Tells how many position should be generated of the regression expression in the hard brackets. Can be only be used in conjunction direct
 * after a hard brackets notation.
 * </p>
 * 
 * <h3>Example of parameter</h3>
 * <p>
 * Parameter: '+46-31-[0-9][0-9][0-9][0-9][0-9][0-9]'<br/>
 * Generate a phone number using a country code and a defined area code and then let the class generate a six position number. First seven positions use the
 * defined values '+46-31-' and for the rest of the positions, generate a randomized value between 0-9.
 * </p>
 * <p>
 * Parameter: '+46-31-[0-9]{6}'<br/>
 * The same as previous example, but we use the curly brackets instead of writing for each position.
 * </p>
 * 
 * <p>
 * Of course the combination of both numerical and string characters can be done. The expression [0-9a-z] is a valid expression.
 * </p>
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sequence extends AbstractColumn {

    private static final Random RANDOM = new Random();
    private static final char REGEXP_ESCAPE_CHARACTER = '\\';
    private static final String AVAILABLE_CHARACTER = "abcdefghijklmnopqrstuvwxyzåäöABCDEFGHIJKLMNOPQRSTUVWXYZÅÄÖ0123456789-+(){}[]";
    private static final String REGEXP_FOR_HARD_AND_CURLY_BRACKETS = "^(\\[.*\\])\\{(\\d*)\\}$";

    private Map<Integer, SequenceData> sequenceDataMap = new HashMap<>();

    public Sequence(final String parameter) {
        super(parameter);
        initSequenceData();
    }

    @Override
    public String generate() {
        String generatedValue = "";
        for (Map.Entry<Integer, SequenceData> entry : sequenceDataMap.entrySet()) {
            SequenceData sequenceData = entry.getValue();
            for (int i = 0; i < sequenceData.getNumberOfTimes(); i++) {
                int n = sequenceData.getCharacters().length();
                int randomInt = RANDOM.nextInt(n);
                generatedValue = generatedValue + sequenceData.getCharacters().charAt(randomInt);
            }
        }
        return generatedValue;
    }

    /**
     * 
     */
    private void initSequenceData() {
        int groupNumber = 0;
        int endIndex;
        for (int startIndex = 0; startIndex < parameter.length(); startIndex = endIndex + 1) {
            String regExp;
            int numberOfTimes = 1;
            if (parameter.charAt(startIndex) == REGEXP_ESCAPE_CHARACTER) {
                endIndex = findEscapeCharacter(startIndex + 1);
                regExp = parameter.substring(startIndex, endIndex + 1);
            } else {
                endIndex = findRegExpGroup(startIndex);
                // Check if the group contains curly brackets {}...
                regExp = parameter.substring(startIndex, endIndex + 1);
                Pattern pattern = Pattern.compile(REGEXP_FOR_HARD_AND_CURLY_BRACKETS);
                Matcher m = pattern.matcher(regExp);
                while (m.find()) {
                    regExp = m.group(1);
                    numberOfTimes = Integer.parseInt(m.group(2));
                }

            }
            Pattern pattern = Pattern.compile(regExp);
            Matcher m = pattern.matcher(AVAILABLE_CHARACTER);
            String characters = "";
            while (m.find()) {
                characters = characters + AVAILABLE_CHARACTER.substring(m.start(), m.end());
            }
            SequenceData sequenceData = new SequenceData(regExp, characters, numberOfTimes);
            sequenceDataMap.put(groupNumber, sequenceData);
            groupNumber++;
        }
    }

    /**
     * 
     * @param startIndex
     * @return
     */
    private int findEscapeCharacter(final int startIndex) {
        int endIndex = startIndex;
        if (parameter.charAt(startIndex) == REGEXP_ESCAPE_CHARACTER) {
            endIndex = findEscapeCharacter(startIndex + 1);
        }
        return endIndex;
    }

    /**
     * Find and adds a regression expression to the group.
     * 
     * @param startIndex
     * @return
     */
    private int findRegExpGroup(final int startIndex) {
        int returnValue = startIndex;
        if (parameter.charAt(startIndex) == '[') {
            returnValue = findRegExpGroup(returnValue, ']');
            if (returnValue + 1 < parameter.length() && parameter.charAt(returnValue + 1) == '{') {
                returnValue = findRegExpGroup(returnValue + 2, '}');
            }
        }
        return returnValue;
    }

    /**
     * 
     * @param startIndex
     * @param c
     * @return
     */
    private int findRegExpGroup(final int startIndex, final char c) {
        int returnValue = startIndex;
        while (returnValue < parameter.length() && parameter.charAt(returnValue) != c) {
            returnValue++;
        }
        return returnValue;
    }

}
