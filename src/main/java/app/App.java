package app;

import app.enums.EventType;
import app.loggers.CashFileLogger;
import app.loggers.EventLogger;
import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Map;

public class App {
    private Client client;
    private CashFileLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;
    private static final Logger LOGGER = Logger.getLogger(App.class);

    public App(Client client, CashFileLogger defaultLogger, Map<EventType,EventLogger> loggers){
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        app.logEvent(EventType.INFO, (Event) ctx.getBean("event"));
        app.logEvent(EventType.ERROR, (Event) ctx.getBean("event"));
        ctx.close();
    }

    public void logEvent(EventType type, Event event) {
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = defaultLogger;
        }
        try {
            logger.logEvent(event);
        } catch (IOException e) {
            LOGGER.error("Can't log event", e);
        }
    }

}
