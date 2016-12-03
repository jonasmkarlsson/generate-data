package org.jonasmkarlsson.generatedata.column;

public class Street extends AbstractFileColumn {

	/**
	 * Default constructor.
	 * 
	 * @see AbstractFileColumn
	 *
	 * @param parameter the parameter
	 */
	public Street(final String parameter) {
		super(parameter);
	}

	/**
	 * Returns the filename for street names
	 */
	@Override
	public String getFilename() {
		return "street.txt";
	}

}
