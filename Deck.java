import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    ArrayList<Card> deck = new ArrayList<Card>();
    String[] suits = {"heart", "spade", "club", "diamond"};

    public Deck() {
        for (int suit_index = 0; suit_index < 4; suit_index++) {
            for (int card_rank = 2; card_rank < 15; card_rank++) {
                addCard(new Card(card_rank, suits[suit_index]));
            }
        }
        Collections.shuffle(deck);

//        for (Card element : deck) {
//            System.out.println(element.getRank() + " " + element.getSuit());
//        }
    }

     public void addCard(Card card) {
        if (deck.size() < 52) {
            deck.add(card);
        }
    }

    public Card get_card(){
        Card  card_  =  null ;
        if (deck.size()  >  0 ){
            card_  = deck.remove(deck.size() - 1 ) ;
        }
        return  card_;
    }
}