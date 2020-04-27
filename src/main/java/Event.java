import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.util.Date;

public class Event {
    static int id = 0;
    @Setter
    @Getter
    String msg;
    Date date;
    DateFormat df;

    public Event(Date date, DateFormat df) {
        id = id + 1;
        this.date = date;
        this.df = df;
    }

    public String toString() {
        return String.format("id = %s, %s: %s", id, msg, df.format(date));
    }

}
