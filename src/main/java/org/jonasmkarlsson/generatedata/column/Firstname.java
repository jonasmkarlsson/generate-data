package org.jonasmkarlsson.generatedata.column;

public class Firstname extends AbstractFileColumn {

	/**
	 * Default constructor.
	 * 
	 * @see AbstractFileColumn
	 *
	 * @param parameter the parameter
	 */
	public Firstname(final String parameter) {
		super(parameter);
	}

	/**
	 * Returns the filename for street names
	 */
	@Override
	public String getFilename() {
		return "firstname.txt";
	}

}
