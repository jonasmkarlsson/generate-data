package org.jonasmkarlsson.generatedata.column;

public class Firstname extends AbstractFileColumn {

    public Firstname(final String parameter) {
        super(parameter);
    }

    @Override
    public String getFilename() {
        return "firstname.txt";
    }

}
