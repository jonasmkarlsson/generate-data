package org.jonasmkarlsson.generatedata.column;

/**
 * Responsible for initials, if necessary, and generate a value for the specific column.
 * 
 * @author jonasmkarlsson
 * 
 */
public abstract class AbstractColumn {

	protected String parameter;

	/**
	 * Default constructor
	 * 
	 * @param parameter the parameters to the column. Example are 'abstract(10)' where the parameter is '10'. Always stored as a string.
	 */
	public AbstractColumn(final String parameter) {
		super();
		this.parameter = parameter;
	}

	/**
	 * Generates a value.
	 * 
	 * @return the generated value
	 */
	public abstract String generate();

	/**
	 * @return the parameter
	 */
	public String getParameter() {
		return parameter;
	}

	/**
	 * @param parameter the parameter to set
	 */
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

}
