import lombok.AllArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

@AllArgsConstructor
public class App {
    Client client;
    CashFileLogger cashFileLogger;

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        app.logEvent((Event) ctx.getBean("event"));
        ctx.close();
    }

    public void logEvent(Event event){
        event.setMsg("Message 1");
        cashFileLogger.logEvent(event);
    }

}
