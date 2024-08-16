package EssentialUtil;

import cardLogic.Card;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private final ArrayList<Card> deck;

    public Deck() {
        this.deck = new ArrayList<>();
    }
    public Card getTop(){
            return deck.get(this.getSize()-1);
    }
    public void addCard (Card card){
        deck.add(card);
    }
    public int getSize(){
        return deck.size();
    }
    public ArrayList<Card> drawCards(int amount){
        int size=this.getSize();
        if(amount>size)return null; //check pile

        ArrayList<Card> cardDrown=new ArrayList<>();
        for(int i=1;i<=amount;i++){
            cardDrown.add(deck.get(size-i));
            deck.remove(size-i);
        }
        return cardDrown;
    }
    public void shuffleDeck(){
        Collections.shuffle(deck);
    }
    public void deleteAll(){
        deck.clear();
    }



}
