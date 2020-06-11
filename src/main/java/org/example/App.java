package org.example;

import org.example.beans.Client;
import org.example.logger.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {

    Client client;
    EventLogger defaultLogger;
    Map<EventType, EventLogger> loggers;
    Event event;

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers, Event event) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
        this.event = event;
    }

    public static void main(String[] args ) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        app.logEvent(EventType.INFO, "test: 1");
        app.logEvent(EventType.ERROR, "test: 1");
        ctx.close();
    }


    public void logEvent(EventType eventType, String msg){
        EventLogger eventLogger = loggers.get(eventType);
        String message = client.getGreeting() + msg.replaceAll(String.valueOf(client.getId()), client.getName());
        event.setMsg(message);
        if (eventLogger == null){
            eventLogger = defaultLogger;
        }
        eventLogger.logEvent(event);
    }



}
