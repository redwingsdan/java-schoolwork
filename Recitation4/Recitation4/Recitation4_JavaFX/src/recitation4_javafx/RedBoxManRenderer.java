package recitation4_javafx;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 *
 * @author McKillaGorilla
 */
public class RedBoxManRenderer extends Application {
    Canvas canvas;
    GraphicsContext gc;
    ArrayList<Point2D> imagesRedBoxManLocations;
    ArrayList<Point2D> shapesRedBoxManLocations;
    Image redBoxManImage;
    
    @Override
    public void start(Stage primaryStage) {
	// INIT THE DATA MANAGERS
	imagesRedBoxManLocations = new ArrayList();
	shapesRedBoxManLocations = new ArrayList();
	
	// LOAD THE RED BOX MAN IMAGE
	redBoxManImage = new Image("RedBoxMan.png");
	
	// MAKE THE CANVAS
	canvas = new Canvas();
	canvas.setStyle("-fx-background-color: cyan");
	gc = canvas.getGraphicsContext2D();

	// PUT THE CANVAS IN A CONTAINER
	Group root = new Group();
	root.getChildren().add(canvas);
	
	canvas.setOnMouseClicked(e->{
	    if (e.isShiftDown()) {
		shapesRedBoxManLocations.add(new Point2D(e.getX(), e.getY()));
		render();
	    }
	    else if (e.isControlDown()) {
		imagesRedBoxManLocations.add(new Point2D(e.getX(), e.getY()));
		render();
	    }
	    else {
		clear();
	    }
	});
	
	// PUT THE CONTAINER IN A SCENE
	Scene scene = new Scene(root, 800, 600);
	canvas.setWidth(scene.getWidth());
	canvas.setHeight(scene.getHeight());

	// AND START UP THE WINDOW
	primaryStage.setTitle("Red Box Man Renderer");
	primaryStage.setScene(scene);
	primaryStage.show();
    }
    
    public void clearCanvas() {
	gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    
    public void clear() {
	shapesRedBoxManLocations.clear();
	imagesRedBoxManLocations.clear();
	render();
    }
    
    public void render() {
	clearCanvas();
	for (int i = 0; i < shapesRedBoxManLocations.size(); i++) {
	    renderShapeRedBoxMan(shapesRedBoxManLocations.get(i));
	}
	for (int j = 0; j < imagesRedBoxManLocations.size(); j++) {
	    renderImageRedBoxMan(imagesRedBoxManLocations.get(j));
	}
    }
    
    public void renderShapeRedBoxMan(Point2D location) {
	/*
        String headColor = "#DD0000";
	String outlineColor = "#000000";
	int headW = 115;
	int headH = 88;
    
	// DRAW HIS RED HEAD
        gc.setFill(Paint.valueOf(headColor));
	gc.fillRect(location.getX(), location.getY(), headW, headH);
        gc.beginPath();
	gc.setStroke(Paint.valueOf(outlineColor));
	gc.setLineWidth(1);
	gc.rect(location.getX(), location.getY(), headW, headH);
	gc.stroke();
        */
	gc.drawImage(redBoxManImage, location.getX(), location.getY());
	// AND THEN DRAW THE REST OF HIM
    }
    
    public void renderImageRedBoxMan(Point2D location) {
	//gc.drawImage(redBoxManImage, location.getX(), location.getY());
        String bodyColor = "#000000";
        String headColor = "#0000FF";
	String outlineColor = "#000000";
        String eyeColor = "#FFFF00";
	int headW = 115;
	int headH = 88;
        int bodyW = 55;
        int bodyH = 25;
        int legW = 30;
        int legH = 10;
        int feetW = 6;
        int feetH = 6;
        int mouthW = 85;
        int mouthH = 10;
        int eyeW = 30;
        int eyeH = 25;
    
	// DRAW HIS RED HEAD
        gc.setFill(Paint.valueOf(headColor));
	gc.fillRect(location.getX(), location.getY(), headW, headH);
        gc.beginPath();
	gc.setStroke(Paint.valueOf(outlineColor));
	gc.setLineWidth(1);
	gc.rect(location.getX(), location.getY(), headW, headH);
	gc.stroke();
        
        gc.setFill(Paint.valueOf(bodyColor));
	gc.fillRect(location.getX()+32, location.getY()+88, bodyW, bodyH);
        gc.beginPath();
	gc.setStroke(Paint.valueOf(outlineColor));
	gc.setLineWidth(1);
	gc.rect(location.getX()+32, location.getY()+88, bodyW, bodyH);
	gc.stroke();
        
        gc.setFill(Paint.valueOf(bodyColor));
	gc.fillRect(location.getX()+45, location.getY()+112, legW, legH);
        gc.beginPath();
	gc.setStroke(Paint.valueOf(outlineColor));
	gc.setLineWidth(1);
	gc.rect(location.getX()+45, location.getY()+112, legW, legH);
	gc.stroke();
        
        gc.setFill(Paint.valueOf(bodyColor));
	gc.fillRect(location.getX()+40, location.getY()+122, feetW, feetH);
        gc.beginPath();
	gc.setStroke(Paint.valueOf(outlineColor));
	gc.setLineWidth(1);
	gc.rect(location.getX()+40, location.getY()+122, feetW, feetH);
	gc.stroke();
        
        gc.setFill(Paint.valueOf(bodyColor));
	gc.fillRect(location.getX()+75, location.getY()+122, feetW, feetH);
        gc.beginPath();
	gc.setStroke(Paint.valueOf(outlineColor));
	gc.setLineWidth(1);
	gc.rect(location.getX()+75, location.getY()+122, feetW, feetH);
	gc.stroke();
        
        gc.setFill(Paint.valueOf(bodyColor));
	gc.fillRect(location.getX()+15, location.getY()+60, mouthW, mouthH);
        gc.beginPath();
	gc.setStroke(Paint.valueOf(outlineColor));
	gc.setLineWidth(1);
	gc.rect(location.getX()+15, location.getY()+60, mouthW, mouthH);
	gc.stroke();
        
        gc.setFill(Paint.valueOf(eyeColor));
	gc.fillRect(location.getX()+15, location.getY()+15, eyeW, eyeH);
        gc.beginPath();
	gc.setStroke(Paint.valueOf(outlineColor));
	gc.setLineWidth(1);
	gc.rect(location.getX()+15, location.getY()+15, eyeW, eyeH);
	gc.stroke();
        
        gc.setFill(Paint.valueOf(eyeColor));
	gc.fillRect(location.getX()+70, location.getY()+15, eyeW, eyeH);
        gc.beginPath();
	gc.setStroke(Paint.valueOf(outlineColor));
	gc.setLineWidth(1);
	gc.rect(location.getX()+70, location.getY()+15, eyeW, eyeH);
	gc.stroke();
        
        gc.setFill(Paint.valueOf(bodyColor));
	gc.fillRect(location.getX()+82, location.getY()+25, feetW, feetH);
        gc.beginPath();
	gc.setStroke(Paint.valueOf(outlineColor));
	gc.setLineWidth(1);
	gc.rect(location.getX()+82, location.getY()+25, feetW, feetH);
	gc.stroke();
        
         gc.setFill(Paint.valueOf(bodyColor));
	gc.fillRect(location.getX()+30, location.getY()+25, feetW, feetH);
        gc.beginPath();
	gc.setStroke(Paint.valueOf(outlineColor));
	gc.setLineWidth(1);
	gc.rect(location.getX()+30, location.getY()+25, feetW, feetH);
	gc.stroke();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	launch(args);
    }
    
}
