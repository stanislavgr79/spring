package org.example.logger;

import org.apache.commons.io.FileUtils;
import org.example.Event;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger{

    Integer cacheSize;
    List<Event> cache;

    public CacheFileEventLogger(String fileName, Integer cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
    }

    @Override
    public void init(){
        cache = new ArrayList<>();
    }

    public void destroy(){
        if(!cache.isEmpty()){
            writeEventsFromCache();
        }
    }

    @Override
    public void logEvent(Event event){
        System.out.println("CacheFileEventLogger: " + event.toString());
        cache.add(event);
        if (cache.size() == cacheSize){
                writeEventsFromCache();
                cache.clear();
        }

    }

    private void writeEventsFromCache(){
        System.out.println("writeEventsFromCache");
        for (Event event: cache) {
            try {
                FileUtils.writeStringToFile(new File(fileName), "\n"+event.toString(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
