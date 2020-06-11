package org.example.logger;

import org.example.Event;

import java.util.List;

public class CombinedEventLogger implements EventLogger{

    List<EventLogger> loggers;

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        for (EventLogger logger: loggers) {
            logger.logEvent(event);
        }
    }
}
