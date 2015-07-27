package org.jonasmkarlsson.generatedata.column;

public class Company extends AbstractFileColumn {

    public Company(final String parameter) {
        super(parameter);
    }

    @Override
    public String getFilename() {
        return "company.txt";
    }

}
