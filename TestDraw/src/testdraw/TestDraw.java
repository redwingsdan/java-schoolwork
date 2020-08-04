/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdraw;

import java.awt.MultipleGradientPaint.CycleMethod;
import static java.awt.SystemColor.text;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import javafx.scene.* ;
import javafx.stage.Stage;
import javafx.geometry.* ; // Point2D
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle ;


public class TestDraw extends Application
{
   double starting_point_x, starting_point_y ;

   Group group_for_rectangles = new Group() ;

   Rectangle new_rectangle = null ;
   
   Ellipse new_ellipse = null;

   boolean new_rectangle_is_being_drawn = false ;
   
   boolean new_ellipse_is_being_drawn = false ;
   
   String currentValue = "RECTANGLE";
   

  // Color[] rectangle_colors = { Color.TEAL,   Color.TOMATO,  Color.TURQUOISE,
   //                             Color.VIOLET, Color.YELLOWGREEN, Color.GOLD } ;

 //  int color_index = 0 ;
   
   void setCurrentValue(String s)
   {
       currentValue = s;
   }
   
   String getCurrentValue()
   {
       return currentValue;
   }

   //  The following method adjusts coordinates so that the rectangle
   //  is shown "in a correct way" in relation to the mouse movement.

   void adjust_rectangle_properties( double starting_point_x,
                                     double starting_point_y,
                                     double ending_point_x,
                                     double ending_point_y,
                                     Rectangle given_rectangle )
   {
      given_rectangle.setX( starting_point_x ) ;
      given_rectangle.setY( starting_point_y ) ;
      given_rectangle.setWidth( ending_point_x - starting_point_x ) ;
      given_rectangle.setHeight( ending_point_y - starting_point_y ) ;

      if ( given_rectangle.getWidth() < 0 )
      {
         given_rectangle.setWidth( - given_rectangle.getWidth() ) ;
         given_rectangle.setX( given_rectangle.getX() - given_rectangle.getWidth() ) ;
      }

      if ( given_rectangle.getHeight() < 0 )
      {
         given_rectangle.setHeight( - given_rectangle.getHeight() ) ;
         given_rectangle.setY( given_rectangle.getY() - given_rectangle.getHeight() ) ;
      }
   }
   
   
   void adjust_ellipse_properties( double starting_point_x,
                                     double starting_point_y,
                                     double ending_point_x,
                                     double ending_point_y,
                                     Ellipse given_ellipse )
   {
       //given_ellipse.centerXProperty()
      given_ellipse.setCenterX(starting_point_x) ;
      given_ellipse.setCenterY( starting_point_y ) ;
      given_ellipse.setRadiusX(ending_point_x - starting_point_x ) ;
      given_ellipse.setRadiusY( ending_point_y - starting_point_y ) ;

      if ( given_ellipse.getRadiusY() < 0 )
      {
         given_ellipse.setRadiusY( - given_ellipse.getRadiusY() ) ;
         //given_ellipse.setRadiusX( given_ellipse.getCenterX() - given_ellipse.getRadiusX() ) ;
      }

      if ( given_ellipse.getRadiusX() < 0 )
      {
         given_ellipse.setRadiusX( - given_ellipse.getRadiusX() ) ;
        // given_ellipse.setRadiusY( given_ellipse.getCenterY() - given_ellipse.getRadiusX() ) ;
      }
   }
   
   public HBox addHBox(Slider slider, ColorPicker colorPicker, ColorPicker colorPicker2, ColorPicker colorPicker3) {
    HBox hbox = new HBox();
    hbox.setPadding(new Insets(15, 12, 15, 12));
    hbox.setSpacing(10);
    hbox.setMinWidth(500);
    hbox.setMaxWidth(500);
    hbox.setMinHeight(20);
    hbox.setMinHeight(20);
    //hbox.setStyle("-fx-background-color: #336699;");

   // Button buttonCurrent = new Button("Current");
   // buttonCurrent.setPrefSize(100, 20);

    //Button buttonProjected = new Button("Projected");
    //buttonProjected.setPrefSize(100, 20);
    
      slider.setShowTickMarks(true);
      slider.setShowTickLabels(true);
      slider.setMajorTickUnit(2);
      slider.setMinorTickCount(1);
      slider.setBlockIncrement(1);
      slider.setLayoutX(600);
      slider.setLayoutY(0);
     
      
      
      colorPicker.setValue(Color.RED);
      colorPicker.setLayoutX(5);
      colorPicker.setLayoutY(0);
      
      
      
      
      colorPicker2.setValue(Color.BLUE);
      colorPicker2.setLayoutX(200);
      colorPicker2.setLayoutY(0);
      
      
      
      
      
      colorPicker3.setValue(Color.BEIGE);
      colorPicker3.setLayoutX(400);
      colorPicker3.setLayoutY(0);
      
    hbox.getChildren().addAll(slider, colorPicker, colorPicker2, colorPicker3);

    return hbox;
}
   
   public VBox addVBox(Button rect, Button ellipse, Button clear){
    VBox vbox = new VBox();
    vbox.setPadding(new Insets(10));
    vbox.setSpacing(8);

    
      rect.setText("RECTANGLE");
      rect.setLayoutY(100);
      
      ellipse.setText("ELLIPSE");
      ellipse.setLayoutY(150);
      
      clear.setText("CLEAR");
      clear.setLayoutY(200);
        
      vbox.getChildren().addAll(rect, ellipse, clear);


    return vbox;
}

/*public void addStackPane(HBox hb) {
    StackPane stack = new StackPane();
    Rectangle helpIcon = new Rectangle(30.0, 25.0);
    helpIcon.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
        new Stop[]{
        new Stop(0,Color.web("#4977A3")),
        new Stop(0.5, Color.web("#B0C6DA")),
        new Stop(1,Color.web("#9CB6CF")),}));
    helpIcon.setStroke(Color.web("#D0E6FA"));
    helpIcon.setArcHeight(3.5);
    helpIcon.setArcWidth(3.5);

    Text helpText = new Text("?");
    helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
    helpText.setFill(Color.WHITE);
    helpText.setStroke(Color.web("#7080A0")); 

    stack.getChildren().addAll(helpIcon, helpText);
    stack.setAlignment(Pos.CENTER_RIGHT);     // Right-justify nodes in stack
    StackPane.setMargin(helpText, new Insets(0, 10, 0, 0)); // Center "?"

    hb.getChildren().add(stack);            // Add to HBox from Example 1-2
    HBox.setHgrow(stack, Priority.ALWAYS);    // Give stack any extra space
}
*/
   public void start( Stage stage )
   {
      stage.setTitle( "DrawingRectanglesFX.java" ) ;
      
      //String currentShape = "Rectangle";
      Slider slider = new Slider(0, 10, 5);
      final ColorPicker colorPicker = new ColorPicker();
      final ColorPicker colorPicker2 = new ColorPicker();
      final ColorPicker colorPicker3 = new ColorPicker();
      Button rect = new Button();
      Button ellipse = new Button();
      Button clear = new Button();
      
      
      
      rect.setOnAction((ActionEvent t) -> {
       setCurrentValue("RECTANGLE");
   });
      
       ellipse.setOnAction((ActionEvent t) -> {
       setCurrentValue("ELLIPSE");
   });
      
       colorPicker.setOnAction((ActionEvent t) -> {
      //new_rectangle.setFill(colorPicker.getValue());
        });
       
       colorPicker2.setOnAction((ActionEvent t) -> {
      //new_rectangle.setFill(colorPicker2.getValue());
        });
       
      
       
        slider.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue2) -> {
      //        new_rectangle.setStrokeWidth(newValue.intValue());
            //test_rectangle.setStrokeWidth(newValue2.intValue());
      });
      
      BorderPane border = new BorderPane();
      BorderPane workspace = new BorderPane();
      //SplitPane workspaceSplit = new SplitPane();
      HBox hbox = addHBox(slider, colorPicker, colorPicker2, colorPicker3);
      border.setTop(hbox);
      border.setLeft(addVBox(rect, ellipse, clear));
    //  addStackPane(hbox);
      group_for_rectangles.setLayoutX(0);
      group_for_rectangles.setLayoutY(0);
      //border.setCenter(group_for_rectangles);
      border.getChildren().add(group_for_rectangles);

      //border.setRight(addFlowPane());
      //workspaceSplit.getItems().add(border);
      //workspaceSplit.getItems().add(workspace);
      workspace.getChildren().add(border);
      Scene scene = new Scene( workspace) ;
      
      scene.setFill( Color.BLACK ) ;
      
       colorPicker3.setOnAction((ActionEvent t) -> {
        scene.setFill(colorPicker3.getValue());
        //group_for_rectangles.setStyle("-fx-background-color: black;");
        
        });
      
      clear.setOnAction((ActionEvent t) ->{
           group_for_rectangles.getChildren().clear();
       // group_for_rectangles.getChildren().addAll(colorPicker, colorPicker2, colorPicker3, slider, rect, ellipse, clear);
       });
      
      scene.setOnMousePressed( ( MouseEvent event ) ->
      {
          System.out.println(getCurrentValue());
         if ( new_rectangle_is_being_drawn == false & getCurrentValue().equals("RECTANGLE"))
         {
            starting_point_x = event.getSceneX() ;
            starting_point_y = event.getSceneY() ;
           // starting_point_x = event.getScreenX();
           //starting_point_x = event.getX();
          // starting_point_y = event.getY();
           // starting_point_y = event.getScreenY();

            new_rectangle = new Rectangle() ;

            // A non-finished rectangle has always the same color.
            new_rectangle.setFill( Color.SNOW ) ; // almost white color
            new_rectangle.setStroke( Color.BLACK ) ;

            group_for_rectangles.getChildren().add( new_rectangle ) ;
   
            new_rectangle_is_being_drawn = true ;
         }
         
         else if( new_ellipse_is_being_drawn == false & getCurrentValue().equals("ELLIPSE"))
         {
             starting_point_x = event.getSceneX() ;
             starting_point_y = event.getSceneY() ;
             
             new_ellipse = new Ellipse();
             
             new_ellipse.setFill(Color.SNOW);
             new_ellipse.setStroke(Color.BLACK);
             
             group_for_rectangles.getChildren().add(new_ellipse);
             
             new_ellipse_is_being_drawn = true;
         }
      } ) ;

      scene.setOnMouseDragged( ( MouseEvent event ) ->
      {
         if ( new_rectangle_is_being_drawn == true & getCurrentValue().equals("RECTANGLE"))
         {
            double current_ending_point_x = event.getSceneX() ;
            double current_ending_point_y = event.getSceneY() ;
            //double current_ending_point_x = event.getX() ;
            //double current_ending_point_y = event.getY() ;
            //double current_ending_point_x = event.getScreenX();
            //double current_ending_point_y = event.getScreenY();

            adjust_rectangle_properties( starting_point_x,
                                         starting_point_y,
                                         current_ending_point_x,
                                         current_ending_point_y,
                                         new_rectangle ) ;
         }
         
         if(new_ellipse_is_being_drawn == true & getCurrentValue().equals("ELLIPSE"))
         {
            double current_ending_point_x = event.getSceneX() ;
            double current_ending_point_y = event.getSceneY() ;

            adjust_ellipse_properties( starting_point_x,
                                         starting_point_y,
                                         current_ending_point_x,
                                         current_ending_point_y,
                                         new_ellipse ) ;
         } 
      } ) ;

      scene.setOnMouseReleased( ( MouseEvent event ) ->
      {
         if ( new_rectangle_is_being_drawn == true & getCurrentValue().equals("RECTANGLE"))
         {
            // Now the drawing of the new rectangle is finished.
            // Let's set the final color for the rectangle.

            new_rectangle.setFill( colorPicker.getValue() ) ;
            new_rectangle.setStroke(colorPicker2.getValue());
            new_rectangle.setStrokeWidth(slider.valueProperty().intValue());
   
            // If all colors have been used we'll start re-using colors from the
            // beginning of the array.

            new_rectangle = null ;
            new_rectangle_is_being_drawn = false ;
         }
         
         if(new_ellipse_is_being_drawn = true & getCurrentValue().equals("ELLIPSE"))
         {
             new_ellipse.setFill(colorPicker.getValue());
             new_ellipse.setStroke(colorPicker2.getValue());
             new_ellipse.setStrokeWidth(slider.valueProperty().intValue());
             
             new_ellipse = null;
             new_ellipse_is_being_drawn = false;
         }
      } ) ;

     // group_for_rectangles.getChildren().addAll(colorPicker, colorPicker2, colorPicker3, slider, rect, ellipse, clear);
      //group_for_rectangles.getChildren().add(colorPicker2);
      //group_for_rectangles.getChildren().add(colorPicker3);
      
      stage.setScene( scene ) ;
      stage.show();
   }


   public static void main( String[] command_line_parameters )
   {
      launch( command_line_parameters ) ;
   }
}
