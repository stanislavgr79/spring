package org.example.logger;

import org.example.Event;

public class ConsoleEventLogger implements EventLogger {

    @Override
    public void logEvent(Event event) {
        System.out.println("ConsoleEventLogger: " + event.toString());
    }


}
