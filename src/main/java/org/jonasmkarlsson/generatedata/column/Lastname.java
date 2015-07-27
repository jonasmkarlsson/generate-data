package org.jonasmkarlsson.generatedata.column;

public class Lastname extends AbstractFileColumn {

    public Lastname(final String parameter) {
        super(parameter);
    }

    @Override
    public String getFilename() {
        return "lastname.txt";
    }

}
