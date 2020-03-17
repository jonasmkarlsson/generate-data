package org.jonasmkarlsson.generatedata;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.jonasmkarlsson.generatedata.column.AbstractColumn;

public class Generate {

	private static final Logger LOGGER = LogManager.getLogger(Generate.class);
	private static final String PACKAGE_NAME_FOR_COLUMNS = "org.jonasmkarlsson.generatedata.column.";
	private static final String REGEXP_FOR_COLUMN_WITH_PARAMETER = "(\\w*)\\((.*)\\)";

	private Generate() {

	}

	/**
	 * Generates a list of columns based on parameters.
	 * 
	 * @see #createAbstractColumns(String[])
	 * 
	 * @param columns the columns as an array of string values
	 * @param numberOfLines the number of lines to generate
	 * @param delimiter the delimiter between each column
	 * @return array of String containing the generated values
	 * @throws ClassNotFoundException if occurs
	 * 
	 */
	public static String[] list(final String[] columns, final int numberOfLines, final String delimiter) throws ClassNotFoundException {
		AbstractColumn[] abstractColumns = createAbstractColumns(columns);
		return list(abstractColumns, numberOfLines, delimiter);
	}

	/**
	 * Generates a list of columns based on parameters.
	 * 
	 * @param columns the columns as an array of AbstractColumn values
	 * @param numberOfLines the number of lines to generate
	 * @param delimiter the delimiter between each column
	 * @return array of String containing the generated values
	 */
	public static String[] list(final AbstractColumn[] columns, final int numberOfLines, final String delimiter) {
		String[] lines = new String[0];
		if (numberOfLines > 0) {
			lines = new String[numberOfLines];
			for (int i = 0; i < numberOfLines; i++) {
				lines[i] = "";
				for (int j = 0; j < columns.length; j++) {
					lines[i] = lines[i] + columns[j].generate() + delimiter;
				}
				// remove last character because it is a delimiter...
				lines[i] = lines[i].substring(0, lines[i].length() - delimiter.length());
			}
		}
		return lines;
	}

	/**
	 * Creates an array of AbstractColumns based on the parameters.
	 * 
	 * @see #createColumn(String)
	 * 
	 * @param columns the columns as an array of Strings values.
	 * @return array of AbstractColumn
	 * @throws Exception if error occurs
	 */
	private static AbstractColumn[] createAbstractColumns(final String[] columns) throws ClassNotFoundException {
		AbstractColumn[] abstractColumns = new AbstractColumn[columns.length];
		for (int i = 0; i < columns.length; i++) {
			try {
				abstractColumns[i] = createColumn(columns[i]);
			} catch (Exception e) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Encountered exception while creating column '" + columns[i] + "':", e);
				}
				LOGGER.info("Encountered exception while creating column '%s'. See log file for more information.", columns[i]);
				throw new ClassNotFoundException();
			}
		}
		return abstractColumns;
	}

	/**
	 * Creates a object (class) instance based on the column name using reflection.
	 * 
	 * @see Constants#PACKAGE_NAME_FOR_COLUMNS
	 * 
	 * @param column the column as string
	 * @return a AbstractColumn if the column exists as a class, otherwise exception.
	 * 
	 * @throws ClassNotFoundException if class can't be created using the column
	 * @throws NoSuchMethodException if class don't have specified a constructor with one string argument
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("java:S4784")
	private static AbstractColumn createColumn(final String column)
	        throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		String parameter = "";
		Pattern columnWithParameter = Pattern.compile(REGEXP_FOR_COLUMN_WITH_PARAMETER);
		Matcher m = columnWithParameter.matcher(column);
		String className = column.trim();
		if (m.matches()) {
			className = m.group(1).trim();
			parameter = m.group(2).trim();
		}
		// First character upper case, the rest lower case.
		className = className.substring(0, 1).toUpperCase() + className.substring(1).toLowerCase();

		// Dynamically create an instance of object...
		Class<?> clazz = Class.forName(PACKAGE_NAME_FOR_COLUMNS + className);
		Constructor<?> ctor = clazz.getConstructor(String.class);
		return (AbstractColumn) ctor.newInstance(parameter);
	}
}
