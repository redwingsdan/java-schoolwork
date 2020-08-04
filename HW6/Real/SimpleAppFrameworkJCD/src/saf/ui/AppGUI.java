package saf.ui;

import properties_manager.PropertiesManager;
import static saf.components.AppStyleArbiter.CLASS_BORDERED_PANE;
import static saf.components.AppStyleArbiter.CLASS_FILE_BUTTON;
import static saf.settings.AppStartupConstants.FILE_PROTOCOL;
import static saf.settings.AppStartupConstants.PATH_IMAGES;
import static saf.settings.AppPropertyType.*;
import saf.controller.AppFileController;
import saf.components.AppStyleArbiter;
import saf.AppTemplate;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tooltip;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


/**
 * This class provides the basic user interface for this application, including
 * all the file controls, but not including the workspace, which would be
 * customly provided for each app.
 *
 * @author Daniel Peterson
 * @version 1.0
 */
public class AppGUI implements AppStyleArbiter {

    // THIS HANDLES INTERACTIONS WITH FILE-RELATED CONTROLS
    protected AppFileController fileController;

    // THIS IS THE APPLICATION WINDOW
    protected Stage primaryStage;

    // THIS IS THE STAGE'S SCENE GRAPH
    protected Scene primaryScene;

    // THIS PANE ORGANIZES THE BIG PICTURE CONTAINERS FOR THE
    // APPLICATION AppGUI
    protected BorderPane appPane;

    // THIS IS THE TOP TOOLBAR AND ITS CONTROLS
    protected HBox toolbar;

    protected HBox fileToolbar;
    protected Button newButton;
    protected Button loadButton;
    protected Button saveButton;
    protected Button saveAsButton;
    protected VBox saveButtonBox;
    protected Button photoButton;
    protected Button codeButton;
    protected Button exitButton;

    protected HBox editToolbar;
    protected Button selectButton;
    protected Button resizeButton;
    protected Button addClassButton;
    protected Button addInterfaceButton;
    protected Button removeButton;
    protected Button undoButton;
    protected Button redoButton;

    protected HBox viewToolbar;
    protected Button zoomInButton;
    protected Button zoomOutButton;
    protected CheckBox gridCkBox;
    protected CheckBox snapCkBox;
    protected VBox CKBox;

    // HERE ARE OUR DIALOGS
    protected AppYesNoCancelDialogSingleton yesNoCancelDialog;

    protected String appTitle;

    private boolean selectButtonSelected;

    /**
     * This constructor initializes the file toolbar for use.
     *
     * @param initPrimaryStage The window for this application.
     *
     * @param initAppTitle The title of this application, which will appear in
     * the window bar.
     *
     * @param app The app within this gui is used.
     */
    public AppGUI(Stage initPrimaryStage,
            String initAppTitle,
            AppTemplate app) {
        // SAVE THESE FOR LATER
        primaryStage = initPrimaryStage;
        appTitle = initAppTitle;

        // INIT THE TOOLBAR
        initFileToolbar(app);

        // AND FINALLY START UP THE WINDOW (WITHOUT THE WORKSPACE)
        initWindow();
    }

    /**
     * Accessor method for getting the application pane, within which all user
     * interface controls are ultimately placed.
     *
     * @return This application GUI's app pane.
     */
    public BorderPane getAppPane() {
        return appPane;
    }

    /**
     * Accessor method for getting this application's primary stage's, scene.
     *
     * @return This application's window's scene.
     */
    public Scene getPrimaryScene() {
        return primaryScene;
    }

    /**
     * Accessor method for getting this application's window, which is the
     * primary stage within which the full GUI will be placed.
     *
     * @return This application's primary stage (i.e. window).
     */
    public Stage getWindow() {
        return primaryStage;
    }

    /**
     * This method is used to activate/deactivate toolbar buttons when they can
     * and cannot be used so as to provide foolproof design.
     *
     * @param saved Describes whether the loaded Page has been saved or not.
     */
    public void updateToolbarControls(boolean saved) {
        // THIS TOGGLES WITH WHETHER THE CURRENT COURSE
        // HAS BEEN SAVED OR NOT
        
        newButton.setDisable(false);
        loadButton.setDisable(false);    
        saveButton.setDisable(saved);
        saveAsButton.setDisable(saved);
        photoButton.setDisable(false);
        codeButton.setDisable(false);
        exitButton.setDisable(false);
        
        selectButton.setDisable(true);
        addClassButton.setDisable(false);
        addInterfaceButton.setDisable(false);
        zoomInButton.setDisable(false);
        zoomOutButton.setDisable(false);
        resizeButton.setDisable(false);
    }

    /**
     * This function initializes all the buttons in the toolbar at the top of
     * the application window. These are related to file management.
     */
    private void initFileToolbar(AppTemplate app) {
        toolbar = new HBox(0);
        fileController = new AppFileController(app);

        fileToolbar = new HBox();
        
        newButton = initChildButton(fileToolbar, NEW_ICON.toString(), NEW_TOOLTIP.toString(), false, 50, 50);
        
        newButton.setOnAction(e -> {
            fileController.handleNewRequest();
        });
        
        loadButton = initChildButton(fileToolbar, LOAD_ICON.toString(), LOAD_TOOLTIP.toString(), false, 50, 50);
        
        loadButton.setOnAction(e -> {
            fileController.handleLoadRequest();
        });
        
        saveButton = initChildButton(fileToolbar, SAVE_ICON.toString(), SAVE_TOOLTIP.toString(), true, 50, 50);
        
        saveButton.setOnAction(e -> {
            fileController.handleSaveRequest();
        });
        
        saveAsButton = initChildButton(fileToolbar, SAVE_AS_ICON.toString(), SAVE_AS_TOOLTIP.toString(), true, 50, 50);
        saveButtonBox = new VBox();
        
        photoButton = initChildButton(saveButtonBox, PHOTO_ICON.toString(), PHOTO_TOOLTIP.toString(), true, 25, 50);
        
        codeButton = initChildButton(saveButtonBox, CODE_ICON.toString(), CODE_TOOLTIP.toString(), true, 25, 50);
        
        fileToolbar.getChildren().add(saveButtonBox);
        
        exitButton = initChildButton(fileToolbar, EXIT_ICON.toString(), EXIT_TOOLTIP.toString(), false, 50, 50);
        
        exitButton.setOnAction(e -> {
            fileController.handleExitRequest();
        });

        editToolbar = new HBox();
        
        selectButton = initChildButton(editToolbar, SELECT_ICON.toString(), SELECT_TOOLTIP.toString(), true, 50, 50);
        
        resizeButton = initChildButton(editToolbar, RESIZE_ICON.toString(), RESIZE_TOOLTIP.toString(), true, 50, 50);
        
        addClassButton = initChildButton(editToolbar, ADD_CLASS_ICON.toString(), ADD_CLASS_TOOLTIP.toString(), true, 50, 50);
        
        addInterfaceButton = initChildButton(editToolbar, ADD_INTERFACE_ICON.toString(), ADD_INTERFACE_TOOLTIP.toString(), true, 50, 50);
        
        removeButton = initChildButton(editToolbar, REMOVE_ICON.toString(), REMOVE_TOOLTIP.toString(), false, 50, 50);
        
        undoButton = initChildButton(editToolbar, UNDO_ICON.toString(), UNDO_TOOLTIP.toString(), true, 50, 50);
        
        redoButton = initChildButton(editToolbar, REDO_ICON.toString(), REDO_TOOLTIP.toString(), true, 50, 50);

        viewToolbar = new HBox();
        
        zoomInButton = initChildButton(viewToolbar, ZOOM_IN_ICON.toString(), ZOOM_IN_TOOLTIP.toString(), true, 50, 50);
        
        zoomOutButton = initChildButton(viewToolbar, ZOOM_OUT_ICON.toString(), ZOOM_OUT_TOOLTIP.toString(), true, 50, 50);
        
        gridCkBox = new CheckBox("Grid");
        
        snapCkBox = new CheckBox("Snap");
        
        CKBox = new VBox();
        
        CKBox.getChildren().addAll(gridCkBox, snapCkBox);
        
        viewToolbar.getChildren().addAll(CKBox);

        toolbar.getChildren().addAll(fileToolbar, editToolbar, viewToolbar);
    }

    private void initWindow() {
        primaryStage.setTitle(appTitle);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        appPane = new BorderPane();
        appPane.setTop(toolbar);
        primaryScene = new Scene(appPane);

        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String appIcon = FILE_PROTOCOL + PATH_IMAGES + props.getProperty(APP_LOGO);
        primaryStage.getIcons().add(new Image(appIcon));

        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    /**
     * This is a public helper method for initializing a simple button with an
     * icon and tooltip and placing it into a toolbar.
     *
     * @param toolbar Toolbar pane into which to place this button.
     *
     * @param icon Icon image file name for the button.
     *
     * @param tooltip Tooltip to appear when the user mouses over the button.
     *
     * @param disabled true if the button is to start off disabled, false
     * otherwise.
     * 
     * @param h Height of icon
     * 
     * @param w Width of icon
     *
     * @return A constructed, fully initialized button placed into its
     * appropriate pane container.
     */
    public Button initChildButton(Pane toolbar, String icon, String tooltip, boolean disabled, int h, int w) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        String imagePath = FILE_PROTOCOL + PATH_IMAGES + props.getProperty(icon);
        Image buttonImage = new Image(imagePath);

        Button button = new Button();
        button.setDisable(disabled);
        button.setGraphic(new ImageView(buttonImage));
        Tooltip buttonTooltip = new Tooltip(props.getProperty(tooltip));
        button.setTooltip(buttonTooltip);
        
        button.setMinHeight(h);
        button.setPrefHeight(h);
        button.setMaxHeight(h);
        
        button.setMinWidth(w);
        button.setPrefWidth(w);
        button.setMaxWidth(w);

        toolbar.getChildren().add(button);

        return button;
    }

    public Button getAddClassButton() {
        return addClassButton;
    }
    
    public Button getAddInterfaceButton() {
        return addInterfaceButton;
    }

    public Button getSelectButton() {
        return selectButton;
    }
    
    public Button getRemoveButton() {
        return removeButton;
    }

    public boolean getSelectButtonSelected() {
        return selectButtonSelected;
    }

    public void setSelectButtonSelected(boolean selected) {
        selectButtonSelected = selected;
    }

    public Button getNewButton() {
        return newButton;
    }

    public Button getPhotoButton() {
        return photoButton;
    }
    
    public Button getCodeButton() {
        return codeButton;
    }
    
    public Button getZoomInButton() {
        return zoomInButton;
    }
    
    public Button getZoomOutButton() {
        return zoomOutButton;
    }
    
    public Button getResizeButton() {
        return resizeButton;
    }
    
    public Button getSaveButton() {
        return saveButton;
    }
     
    public Button getUndoButton() {
        return undoButton;
    } 
    
    public Button getRedoButton() {
        return redoButton;
    }

    public HBox getToolbarPane() {
        return toolbar;
    }
    
    /**
     * This function specifies the CSS style classes for the controls managed by
     * this framework.
     */
    @Override
    public void initStyle() {
        toolbar.getStyleClass().add(CLASS_BORDERED_PANE);
        newButton.getStyleClass().add(CLASS_FILE_BUTTON);
        loadButton.getStyleClass().add(CLASS_FILE_BUTTON);
        saveButton.getStyleClass().add(CLASS_FILE_BUTTON);
        exitButton.getStyleClass().add(CLASS_FILE_BUTTON);
        fileToolbar.getStyleClass().add("toolbars");
        editToolbar.getStyleClass().add("toolbars");
        viewToolbar.getStyleClass().add("toolbars");
        CKBox.getStyleClass().add("check_vbox");
    }
}
