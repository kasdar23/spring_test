package app.loggers;

import app.Event;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Collection;

@AllArgsConstructor
public class CombineEventLogger implements EventLogger {
    private Collection<EventLogger> loggers;

    public void logEvent(Event event) throws IOException {
        for (EventLogger logger : loggers) {
            logger.logEvent(event);
        }

    }
}
