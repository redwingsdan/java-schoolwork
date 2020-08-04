
import javafx.event.Event;
import javafx.event.EventHandler;

public class StartHandler implements EventHandler {
    private ThreadExample app;
    
    public StartHandler(ThreadExample initApp) {
	app = initApp;
    }
    
    @Override
    public void handle(Event event) {
	app.startWork();
    }
    
}
