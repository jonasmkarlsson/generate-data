package org.jonasmkarlsson.generatedata.column;

public class Location extends AbstractFileColumn {

    public Location(final String parameter) {
        super(parameter);
    }

    @Override
    public String getFilename() {
        return "location.txt";
    }

}
