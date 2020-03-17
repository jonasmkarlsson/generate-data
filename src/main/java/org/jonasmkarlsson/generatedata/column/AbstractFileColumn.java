package org.jonasmkarlsson.generatedata.column;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.security.SecureRandom;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public abstract class AbstractFileColumn extends AbstractColumn {

	private static final Logger LOGGER = LogManager.getLogger(AbstractFileColumn.class);
	private static final SecureRandom RANDOM = new SecureRandom();
	private static final String FILE_COMMENT_STARTS_WITH_CHARACTER = "#";

	private String[] fileLines = new String[0];

	/**
	 * Default constructor
	 * 
	 * @param parameter the parameter to be used
	 */
	public AbstractFileColumn(final String parameter) {
		super(parameter);
		init();
	}

	/**
	 * Gets the filename of the file
	 * 
	 * @return the filename
	 */
	public abstract String getFilename();

	public void init() {
		InputStream is = this.getClass().getResourceAsStream(getFilename());
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
			this.fileLines = readLines(bufferedReader);
			is.close();
		} catch (NullPointerException npe) {
			LOGGER.error(bufferedReader, npe);
		} catch (IOException io) {
			LOGGER.error(bufferedReader, io);
		}
	}

	/**
	 * Generates/gets the value from a random place in the array fileLines.
	 * 
	 * @return a String containing the value
	 */
	@Override
	public String generate() {
		int randomInt = RANDOM.nextInt(fileLines.length);
		return fileLines[randomInt];
	}

	/**
	 * Reads a file row for row, and return an array of all rows in filename.
	 * 
	 * @param filename the filename to read
	 * @return array of Strings containing rows.
	 */
	private String[] readLines(BufferedReader bufferedReader) throws IOException {
		List<String> lines = new ArrayList<>();
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			if (!line.startsWith(FILE_COMMENT_STARTS_WITH_CHARACTER)) {
				lines.add(line);
			}
		}
		return lines.toArray(new String[lines.size()]);
	}

}
