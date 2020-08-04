
import javafx.application.Platform;
import javafx.concurrent.Task;

public class CounterTask extends Task<Void> {

    private ThreadExample app;
    private int counter;

    public CounterTask(ThreadExample initApp) {
	app = initApp;
	counter = 0;
    }

    @Override
    protected Void call() throws Exception {
	while (true) {
	    if (app.doWork()) {
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
			counter++;
			String text = "\tIteration: " + counter + "\n";
			app.appendText(text);
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
