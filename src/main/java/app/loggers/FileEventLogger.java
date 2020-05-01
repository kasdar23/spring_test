package app.loggers;

import app.Event;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    private String fileName;
    private File file;
    private static final Logger LOGGER = Logger.getLogger(FileEventLogger.class);


    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void init() {
        this.file = new File(fileName);
        if (!file.canWrite())
            System.out.println("Can't write into the file");
    }

    public void logEvent(Event event) throws IOException {
        FileUtils.writeStringToFile(file, event.toString(), "ISO-8859-1");
    }
}
