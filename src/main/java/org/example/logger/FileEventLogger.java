package org.example.logger;

import org.example.Event;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger{

    String fileName;
    File file;
    boolean isWritable;
    CacheFileEventLogger cacheFileEventLogger;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void init() throws IOException {
        this.file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("failed create file ");
            e.printStackTrace();
        }
        isWritable = file.canWrite();
    }

    @Override
    public void logEvent(Event event) {
        if(isWritable) {
            cacheFileEventLogger.logEvent(event);
        }
        System.out.println("FileEventLogger: " + event.toString());
    }

    public File getFile() {
        return file;
    }

    public CacheFileEventLogger getCacheFileEventLogger() {
        return cacheFileEventLogger;
    }

    public void setCacheFileEventLogger(CacheFileEventLogger cacheFileEventLogger) {
        this.cacheFileEventLogger = cacheFileEventLogger;
    }
}
