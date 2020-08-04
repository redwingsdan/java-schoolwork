import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {
    private final String suit;
    private final int rank;
    private Image front_image;
    final int  CARD_WIDTH   =  150;
    final int  CARD_HEIGHT  =  215;

    public Card(int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
        String image_file_name = "images//Playing_card_" + suit +"_"+ rank + ".jpg" ;
        this.front_image = new Image( image_file_name ) ;
        ImageView imageView = new ImageView(this.front_image);
        imageView.setFitWidth(CARD_WIDTH);
        imageView.setPreserveRatio(true);
    }

    public String getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public Image getFront_image() {
        return front_image;
    }

    public void setFront_image(Image front_image) {
        this.front_image = front_image;
    }
}