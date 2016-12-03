package org.jonasmkarlsson.generatedata.column;

public class Company extends AbstractFileColumn {

	/**
	 * Default constructor.
	 * 
	 * @see AbstractFileColumn
	 *
	 * @param parameter the parameter
	 */
	public Company(final String parameter) {
		super(parameter);
	}

	/**
	 * Returns the filename for street names
	 */
	@Override
	public String getFilename() {
		return "company.txt";
	}

}
