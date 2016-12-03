package org.jonasmkarlsson.generatedata.column;

public class Lastname extends AbstractFileColumn {

	/**
	 * Default constructor.
	 * 
	 * @see AbstractFileColumn
	 *
	 * @param parameter the parameter
	 */
	public Lastname(final String parameter) {
		super(parameter);
	}

	/**
	 * Returns the filename for street names
	 */
	@Override
	public String getFilename() {
		return "lastname.txt";
	}

}
