package org.jonasmkarlsson.generatedata;

/**
 * Command Line Interface (CLI) for Generate Data project.
 * 
 * @author Jonas M Karlsson
 */
public class Main {

	/**
	 * Default constructor
	 */
	@SuppressWarnings("all")
	public Main() {

	}

	/**
	 * Main executable method .
	 * 
	 * @param commandLineArguments the command line arguments.
	 */
	public static void main(final String[] commandLineArguments) {
		new Launcher().run(commandLineArguments);
	}

}
