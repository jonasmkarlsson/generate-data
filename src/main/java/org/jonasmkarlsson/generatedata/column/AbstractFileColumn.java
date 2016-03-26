package org.jonasmkarlsson.generatedata.column;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

public abstract class AbstractFileColumn extends AbstractColumn {

    private static final Logger LOGGER = Logger.getLogger(AbstractFileColumn.class);
    private static final Random RANDOM = new Random();
    private static final String FILE_COMMENT_STARTS_WITH_CHARACTER = "#";

    private String[] fileLines = new String[0];

    public AbstractFileColumn(final String parameter) {
        super(parameter);
        init();
    }

    public abstract String getFilename();

    public void init() {
        InputStream is = this.getClass().getResourceAsStream(getFilename());
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            this.fileLines = readLines(bufferedReader);
            is.close();
        } catch (NullPointerException npe) {
            LOGGER.error(bufferedReader, npe);
        } catch (IOException io) {
            LOGGER.error(bufferedReader, io);
        }
    }

    @Override
    public String generate() {
        int randomInt = RANDOM.nextInt(fileLines.length);
        return fileLines[randomInt];
    }

    /**
     * Reads a file row for row, and return an array of all rows in filename.
     * 
     * @param filename the filename to read
     * @return array of Strings containing rows.
     */
    private String[] readLines(BufferedReader bufferedReader) throws IOException {
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (!line.startsWith(FILE_COMMENT_STARTS_WITH_CHARACTER)) {
                lines.add(line);
            }
        }
        return lines.toArray(new String[lines.size()]);
    }

}
