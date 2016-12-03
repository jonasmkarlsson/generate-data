package org.jonasmkarlsson.generatedata.column;

public class Location extends AbstractFileColumn {

	/**
	 * Default constructor.
	 * 
	 * @see AbstractFileColumn
	 *
	 * @param parameter the parameter
	 */
	public Location(final String parameter) {
		super(parameter);
	}

	/**
	 * Returns the filename for street names
	 */
	@Override
	public String getFilename() {
		return "location.txt";
	}

}
