import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Game extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        Deck deck = new Deck();
        ArrayList<Card> player_1 = new ArrayList<>();

        BorderPane borderPane = new BorderPane();
        HBox topPane = new HBox();
        HBox centerPane = new HBox();
        HBox bottomPane = new HBox();
        VBox leftPane = new VBox();
        VBox rightPane = new VBox();

        topPane.setAlignment(Pos.CENTER);
        centerPane.setAlignment(Pos.CENTER);
        bottomPane.setAlignment(Pos.CENTER);
        leftPane.setAlignment(Pos.CENTER);
        rightPane.setAlignment(Pos.CENTER);

        for(int i = 0; i < 13; i++)
        {
            player_1.add(deck.get_card());
            topPane.getChildren().add(new ImageView(player_1.get(i));
        }
        borderPane.setTop(topPane);
        borderPane.setRight(rightPane);
        borderPane.setBottom(bottomPane);
        borderPane.setLeft(leftPane);

        Scene scene = new Scene(borderPane,1200, 600);
        primaryStage.setTitle("Bridge");
        borderPane.setBackground(null) ;
        scene.setFill(Color.GREEN) ;
        primaryStage.setScene( scene ) ;
        primaryStage.show() ;
    }


    public static void main(String[] args) {
        launch(args);
    }
}