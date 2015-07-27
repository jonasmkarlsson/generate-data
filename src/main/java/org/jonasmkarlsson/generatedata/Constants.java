package org.jonasmkarlsson.generatedata;

public class Constants {

    // Properties settings...
    public static final String PROPERTY_APPLICATION = "application";
    public static final String PROPERTY_COMMAND = "command";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_VERSION = "version";
    public static final String PROPERTY_FILE = "generate.properties";

    // Default settings...
    public static final int DEFAULT_NUMBER_OF_LINES = 10;
    public static final int DEFAULT_NUMBER_OF_PHONE_DIGITS = 6;
    public static final String DEFAULT_DELIMITER = ",";
    public static final String DEFAULT_PHONE_DELIMITER = "-";
    public static final int DEFAULT_NUMBER_OF_AVATAR_URLS = 10;
    public static final int MAX_NUMBER_OF_ALLOWED_FIELDS = 100;

    // Options section...
    public static final String[] OPTIONS_LINES = { "n", "lines" };
    public static final String[] OPTIONS_FIELD = { "f", "field" };
    public static final String[] OPTIONS_DELIMITER = { "d", "delimiter" };
    public static final String[] OPTIONS_VERSION = { "v", "version" };

    // Help section...
    public static final String HELP_LINES = "Number of rows to generate. Default are '" + DEFAULT_NUMBER_OF_LINES + "'.";
    public static final String HELP_FIELDS = "Type of field(s) to generate, least one must be specified; company, firstname, lastname, location, street, sequence.";
    public static final String HELP_DELIMITER = "Specify delimiter, only usable when several fields are combined. Default are '" + Constants.DEFAULT_DELIMITER + "'.";
    public static final String HELP_VERSION = "Display version information.";
    public static final String HELP_HELP = "Display help and usage";

    private Constants() {
        super();
    }

}
