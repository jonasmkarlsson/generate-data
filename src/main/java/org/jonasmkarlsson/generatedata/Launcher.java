package org.jonasmkarlsson.generatedata;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;

public class Launcher {
    private static final Logger LOGGER = Logger.getLogger(Launcher.class);

    private final Properties properties;
    private final String application;
    private final String command;

    public Launcher() {
        super();
        properties = readProperties();
        application = properties.getProperty(Constants.PROPERTY_APPLICATION);
        command = properties.getProperty(Constants.PROPERTY_COMMAND);
    }

    /**
     * Parse the command line arguments
     * 
     * @param commandLineArguments the arguments to CLI
     */
    @SuppressWarnings("all")
    public void run(String[] commandLineArguments) {
        final Options gnuOptions = constructGnuOptions();
        final CommandLineParser cmdLineGnuParser = new GnuParser();
        try {
            CommandLine commandLine = cmdLineGnuParser.parse(gnuOptions, commandLineArguments);
            useGnuParser(commandLine);
        } catch (ParseException parseException) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Encountered exception while parsing using GnuParser:", parseException);
            }
            System.out.println("Encountered exception while parsing using GnuParser: " + parseException.getMessage());
        }
    }

    @SuppressWarnings("all")
    private Properties readProperties() {
        Properties prop = new Properties();
        InputStream stream = this.getClass().getResourceAsStream(Constants.PROPERTY_FILE);
        try {
            prop.load(stream);
        } catch (IOException e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Encountered exception while reading property file '" + Constants.PROPERTY_FILE + "':", e);
            }
            LOGGER.info("Encountered exception while reading property file '" + Constants.PROPERTY_FILE + "'. See log file for more information.");
        }
        return prop;
    }

    /**
     * Parse the command line and execute the action.
     * 
     * @param commandLine
     */
    private void useGnuParser(final CommandLine commandLine) {
        // Go through and get values, or default values, for each parameter...
        // Number of lines...
        int numberOfLines = Constants.DEFAULT_NUMBER_OF_LINES;
        String numberOfLinesOptionValue = checkCommandLineForOption(Constants.OPTIONS_LINES, commandLine, Integer.toString(Constants.DEFAULT_NUMBER_OF_LINES));
        try {
            numberOfLines = Integer.parseInt(numberOfLinesOptionValue);
        } catch (NumberFormatException nfe) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Could not convert value '" + numberOfLinesOptionValue + "' to an int. Using default value '" + Constants.DEFAULT_NUMBER_OF_LINES + "'.");
            }
        }

        // Delimiter...
        String delimiterOptionValue = checkCommandLineForOption(Constants.OPTIONS_DELIMITER, commandLine, Constants.DEFAULT_DELIMITER);

        // Field...
        String[] fieldOptionValues = checkCommandLineForFieldOptions(Constants.OPTIONS_FIELD, commandLine, Constants.DEFAULT_DELIMITER);

        // Check if any field option value are given
        if (fieldOptionValues != null && fieldOptionValues.length > 0) {
            try {
                generateDataAndPrint(fieldOptionValues, numberOfLines, delimiterOptionValue);
            } catch (Exception e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Could not generate and print data.", e);
                }
            }
        } else {
            printOutOptions(commandLine);
        }
    }

    @SuppressWarnings("all")
    private void printOutOptions(CommandLine commandLine) {
        if (commandLine.hasOption(Constants.OPTIONS_VERSION[0]) || commandLine.hasOption(Constants.OPTIONS_VERSION[1])) {
            System.out.println(application);
        } else {
            printHelp(constructGnuOptions(), 145, "Help GNU", "End of GNU Help", 5, 3, true);
        }
    }

    @SuppressWarnings("all")
    private void generateDataAndPrint(final String[] fieldOptionValues, final int numberOfLines, final String delimiter) throws Exception {
        String[] lines = Generate.list(fieldOptionValues, numberOfLines, delimiter);
        for (String line : lines) {
            System.out.println(line);
        }
    }

    /**
     * Construct and provide GNU-compatible Options.
     * 
     * @return Options expected from command-line of GNU form.
     */
    private Options constructGnuOptions() {
        final Options gnuOptions = new Options();
        gnuOptions.addOption(Constants.OPTIONS_VERSION[0], Constants.OPTIONS_VERSION[1], false, Constants.HELP_VERSION);
        gnuOptions.addOption(Constants.OPTIONS_LINES[0], Constants.OPTIONS_LINES[1], true, Constants.HELP_LINES);
        gnuOptions.addOption(Constants.OPTIONS_DELIMITER[0], Constants.OPTIONS_DELIMITER[1], true, Constants.HELP_DELIMITER);

        Option option = new Option(Constants.OPTIONS_FIELD[0], Constants.OPTIONS_FIELD[1], true, Constants.HELP_FIELDS);
        option.setArgs(Constants.MAX_NUMBER_OF_ALLOWED_FIELDS);
        gnuOptions.addOption(option);

        return gnuOptions;
    }

    /**
     * Write "help" to the provided OutputStream.
     * 
     * @param options the Options
     * @param printedRowWidth the row width
     * @param header the header
     * @param footer the footer
     * @param spacesBeforeOption the spaces before options
     * @param spacesBeforeOptionDescription the spaces before options description
     * @param displayUsage the display usage
     */
    @SuppressWarnings("all")
    private void printHelp(final Options options, final int printedRowWidth, final String header, final String footer, final int spacesBeforeOption,
            final int spacesBeforeOptionDescription, final boolean displayUsage) {
        final PrintWriter writer = new PrintWriter(System.out);
        final HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp(writer, printedRowWidth, command, header, options, spacesBeforeOption, spacesBeforeOptionDescription, footer, displayUsage);
        writer.flush();
    }

    /**
     * Checks the command line after the field options.
     * 
     * @param validOptions the valid options for choosing field parameter
     * @param commandLine the commandLine
     * @param delimiter the delimiter to use when splitting the potential field(s) from option from commandLine.
     * @return String array of selected field(s) or null if not found in command line.
     */
    private String[] checkCommandLineForFieldOptions(String[] validOptions, CommandLine commandLine, String delimiter) {
        String[] returnValue = null;
        String optionValue = checkCommandLineForOption(validOptions, commandLine, null);
        if (optionValue != null) {
            returnValue = optionValue.split(delimiter);
        }
        return returnValue;
    }

    /**
     * Checks the command line after the valid options. If not found, returns the default value.
     * 
     * @param validOptions the valid options for choosing option.
     * @param commandLine the commandLine
     * @param defaultValue the default value
     * @return the value from the valid option and if not found the default value.
     */
    private String checkCommandLineForOption(String[] validOptions, CommandLine commandLine, String defaultValue) {
        String optionValue = defaultValue;
        for (String option : validOptions) {
            if (commandLine.hasOption(option)) {
                optionValue = commandLine.getOptionValue(option);
            }
        }
        return optionValue;
    }

    /**
     * @return the application
     */
    public String getApplication() {
        return application;
    }

    /**
     * @return the properties
     */
    public Properties getProperties() {
        return properties;
    }

}
