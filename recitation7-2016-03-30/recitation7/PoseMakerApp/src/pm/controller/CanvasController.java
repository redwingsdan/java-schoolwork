package pm.controller;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.shape.Shape;
import pm.data.DataManager;
import pm.data.Draggable;
import pm.data.PoseMakerState;
import static pm.data.PoseMakerState.DRAGGING_NOTHING;
import static pm.data.PoseMakerState.DRAGGING_SHAPE;
import static pm.data.PoseMakerState.SELECTING_SHAPE;
import static pm.data.PoseMakerState.SIZING_SHAPE;
import pm.gui.Workspace;
import saf.AppTemplate;

/**
 * This class responds to interactions with the rendering surface.
 * 
 * @author McKillaGorilla
 * @version 1.0
 */
public class CanvasController {
    AppTemplate app;
    
    public CanvasController(AppTemplate initApp) {
	app = initApp;
    }
    
    public void processCanvasMouseExited(int x, int y) {
	DataManager dataManager = (DataManager)app.getDataComponent();
	if (dataManager.isInState(PoseMakerState.DRAGGING_SHAPE)) {
	    
	}
	else if (dataManager.isInState(PoseMakerState.SIZING_SHAPE)) {
	    
	}
    }
    
    public void processCanvasMousePress(int x, int y) {
	DataManager dataManager = (DataManager)app.getDataComponent();
	if (dataManager.isInState(SELECTING_SHAPE)) {
	    // SELECT THE TOP SHAPE
	    Shape shape = dataManager.selectTopShape(x, y);
	    Scene scene = app.getGUI().getPrimaryScene();

	    // AND START DRAGGING IT
	    if (shape != null) {
		scene.setCursor(Cursor.MOVE);
		dataManager.setState(PoseMakerState.DRAGGING_SHAPE);
		app.getGUI().updateToolbarControls(false);
	    }
	    else {
		scene.setCursor(Cursor.DEFAULT);
		dataManager.setState(DRAGGING_NOTHING);
		app.getWorkspaceComponent().reloadWorkspace();
	    }
	}
	else if (dataManager.isInState(PoseMakerState.STARTING_RECTANGLE)) {
	    dataManager.startNewRectangle(x, y);
	}
	else if (dataManager.isInState(PoseMakerState.STARTING_ELLIPSE)) {
	    dataManager.startNewEllipse(x, y);
	}
	Workspace workspace = (Workspace)app.getWorkspaceComponent();
	workspace.reloadWorkspace();
    }
    
    public void processCanvasMouseMoved(int x, int y) {
	//Workspace workspace = (Workspace)app.getWorkspaceComponent();
	//workspace.setDebugText("(" + x + "," + y + ")");
    }
    
    public void processCanvasMouseDragged(int x, int y) {
	DataManager dataManager = (DataManager)app.getDataComponent();
	if (dataManager.isInState(SIZING_SHAPE)) {
	    Draggable newDraggableShape = (Draggable)dataManager.getNewShape();
	    newDraggableShape.size(x, y);
	}
	else if (dataManager.isInState(DRAGGING_SHAPE)) {
	    Draggable selectedDraggableShape = (Draggable)dataManager.getSelectedShape();
	    selectedDraggableShape.drag(x, y);
	    app.getGUI().updateToolbarControls(false);
	}
    }
    
    public void processCanvasMouseRelease(int x, int y) {
	DataManager dataManager = (DataManager)app.getDataComponent();
	if (dataManager.isInState(SIZING_SHAPE)) {
	    dataManager.selectSizedShape();
	    app.getGUI().updateToolbarControls(false);
	}
	else if (dataManager.isInState(PoseMakerState.DRAGGING_SHAPE)) {
	    dataManager.setState(SELECTING_SHAPE);
	    Scene scene = app.getGUI().getPrimaryScene();
	    scene.setCursor(Cursor.DEFAULT);
	    app.getGUI().updateToolbarControls(false);
	}
	else if (dataManager.isInState(PoseMakerState.DRAGGING_NOTHING)) {
	    dataManager.setState(SELECTING_SHAPE);
	}
    }
}
