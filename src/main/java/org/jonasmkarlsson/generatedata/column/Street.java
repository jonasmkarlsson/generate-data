package org.jonasmkarlsson.generatedata.column;

public class Street extends AbstractFileColumn {

    public Street(final String parameter) {
        super(parameter);
    }

    @Override
    public String getFilename() {
        return "street.txt";
    }

}
