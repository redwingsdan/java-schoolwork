package multithreading_examples;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * This class demonstrates how to use JavaFX's AnimationTimer class to perform
 * animation on scene circles.
 */
public class BouncyCircles extends Application {
    // FOR MANAGING OUR NODE STATES
    static final int STOPPED_STATE = 0;
    static final int ANIMATING_STATE = 1;
    int animationState = STOPPED_STATE;

    // WE'LL PUT THE RENDERED NODES HERE
    Stage window;
    Pane canvas = new Pane();

    // DATA FOR OUR RENDERED NODES
    ArrayList<Circle> circles = new ArrayList();
    ArrayList<double[]> circleVelocities = new ArrayList();
    ReentrantLock lock = new ReentrantLock();
    double maxVelocity = 1.0;
    double velocityInc = 2.0;
    
    // THIS WILL MANAGE ANIMATION
    NodeAnimationTimer animationTimer;

    @Override
    public void start(Stage primaryStage) throws Exception {
	initCircles();
	initEventHandling();
	Scene windowScene = new Scene(canvas, 800, 600);
	window = primaryStage;
	window.setScene(windowScene);
	window.show();
    }

    private void initCircles() {
	for (int i = 0; i < 100; i++) {
	    Circle c = new Circle();
	    c.setFill(Color.color(Math.random(), Math.random(), Math.random(), Math.random()));
	    c.setRadius(25);
	    c.setCenterX(-50);
	    c.setCenterY(-50);
	    circles.add(c);
	    double[] velocityVector = new double[2];
	    velocityVector[0] = 0;
	    velocityVector[1] = 0;
	    circleVelocities.add(velocityVector);
	    canvas.getChildren().add(c);
	}
    }

    private void initEventHandling() {
	canvas.setOnMousePressed(e -> {
	    try {
		lock.lock();
		if ((animationState == STOPPED_STATE) && (e.getButton() == MouseButton.PRIMARY) && (e.getClickCount() == 1)) {
		    animationTimer = new NodeAnimationTimer();
		    animationTimer.start();
		    animationState = ANIMATING_STATE;
		}
		else if ((e.getButton() == MouseButton.PRIMARY) && (e.getClickCount() == 2)) {
		    animationTimer.stop();
		    animationState = STOPPED_STATE;
		    resetCircles();
		}
		else if (e.getButton() == MouseButton.PRIMARY) {
		    maxVelocity *= velocityInc;
		    updateCircleVelocities(2);
		}
		else if (e.getButton() == MouseButton.SECONDARY) {
		    maxVelocity /= velocityInc;
		    updateCircleVelocities(.5);
		}
	    } finally {
		lock.unlock();
	    }
	});
    }
    
    class NodeAnimationTimer extends AnimationTimer {
	long lastTime = -1;
	long targetTimePerFrame = 20000000;

	@Override
	public void handle(long now) {
	    try {
		BouncyCircles.this.lock.lock();
		
		// HOW LONG HAS THIS PAST FRAME TAKEN?
		if (lastTime < 0)
		    lastTime = now - targetTimePerFrame;
		long timeDiff = now-lastTime;
		lastTime = now;
		double perc = ((double)timeDiff)/targetTimePerFrame;
		System.out.println(perc);
		
		// SCALE OUR NODE MOVEMENTS ACCORDING TO THE CURRENT FRAME SPEED
		moveAllCircles(perc);
	    }
	    finally {
		// WHAT'S GOING ON HERE?
                lock.unlock();
	    }
	}
	
    }

    public void resetCircles() {
	for (int i = 0; i < circles.size(); i++) {
	    Circle circle = circles.get(i);
	    circle.setCenterX(0);
	    circle.setCenterY(0);

	    // PICK A RANDOM LOCATION
	    circle.setTranslateX(canvas.getWidth() * Math.random());
	    circle.setTranslateY(canvas.getHeight() * Math.random());
	    generateRandomVector(maxVelocity, circleVelocities.get(i));
	}
    }

    public void updateCircleVelocities(double update) {
	for (int i = 0; i < circles.size(); i++) {
	    double v[] = circleVelocities.get(i);
	    v[0] *= update;
	    v[1] *= update;
	}
    }

    public void generateRandomVector(double velocity, double[] v) {
	double randomRadians = Math.PI * 2 * Math.random();
	v[0] = Math.sin(randomRadians) * velocity;
	v[1] = Math.cos(randomRadians) * velocity;
    }
    
    public void moveAllCircles(double perc) {
	for (int i = 0; i < circles.size(); i++) {
	    double v[] = circleVelocities.get(i);
	    Circle circle = circles.get(i);
	    circle.translateXProperty().setValue(circle.getTranslateX() + (v[0] * perc));
	    circle.translateYProperty().setValue(circle.getTranslateY() + (v[1] * perc));
	    if (((v[0] < 0) && ((circle.translateXProperty().getValue() - (circle.getRadius()/2)) < 0))
		    ||
		    ((v[0] > 0) && ((circle.translateXProperty().getValue() + (circle.getRadius()/2)) > canvas.getWidth()))) {
		v[0] *= -1;
	    }
	    if (((v[1] < 0) && ((circle.translateYProperty().getValue() - (circle.getRadius()/2)) < 0))
		    ||
		    ((v[1] > 0) && ((circle.translateYProperty().getValue() + (circle.getRadius()/2)) > canvas.getHeight()))) {
		v[1] *= -1;
	    }
	}
    }

    public static void main(String[] args) {
	launch(args);
    }
}
