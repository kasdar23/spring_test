import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CashFileLogger extends FileEventLogger {
    int cashSize;
    List<Event> cash = new ArrayList<Event>();
    private static final Logger LOGGER = Logger.getLogger(CashFileLogger.class);

    public CashFileLogger(int cashSize, String fileName) {
        super(fileName);
        this.cashSize = cashSize;
    }

    @Override
    public void logEvent(Event event) {
        cash.add(event);
        if (cash.size() == cashSize) {
            writeEventsFromCash();
            cash.clear();
        }
    }

    public void writeEventsFromCash() {
        for (Event event : cash
        ) {
            try {
                super.logEvent(event);
            } catch (IOException e) {
                LOGGER.error("Can't write event from cash", e);
            }
        }
    }

    public void destroy() {
        if (!cash.isEmpty()) {
            writeEventsFromCash();
        }
    }

}
