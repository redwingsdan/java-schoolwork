
import java.util.Date;
import javafx.application.Platform;
import javafx.concurrent.Task;

public class DateTask extends Task<Void> {

    private ThreadExample app;
    private Date now;

    public DateTask(ThreadExample initApp) {
	app = initApp;
    }

    @Override
    protected Void call() throws Exception {
	while (true) {
	    if (app.doWork()) {
		now = new Date();
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
			String time = now.toString() + "\n";
			app.appendText(time);
		    }
		});

		// SLEEP EACH FRAME
		app.sleep(10);
	    } else {
		app.sleep(10);
	    }
	}
    }
}
